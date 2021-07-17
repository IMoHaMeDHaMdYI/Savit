package com.savit.dashboard.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.savit.account.domain.usecase.GetAccountsUseCase
import com.savit.account.presentation.mapper.AccountUiMapper
import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.mapper.AccountPreviewUiMapper
import com.savit.dashboard.presentation.model.AccountUiModel
import com.savit.dashboard.presentation.model.CategoryUiModel
import com.savit.dashboard.presentation.model.RecordUiModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState
import com.savit.record.domain.usecase.GetRecordUseCase
import com.savit.record.domain.usecase.GetRecordsAmountUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class DashboardViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val accountPreviewUiMapper: AccountPreviewUiMapper,
    private val getRecordsUseCase: GetRecordUseCase,
    private val getRecordsAmountUseCase: GetRecordsAmountUseCase
) : BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {

    override val initViewState: DashboardViewState = DashboardViewState(
        accounts = listOf(),
        records = listOf(),
        isAccountsEmpty = true,
        isRecordsEmpty = true
    ).also(::updateViewState)

    private val compositeDisposable = CompositeDisposable()

    override fun postAction(viewAction: DashboardViewAction) {
        when (viewAction) {
            DashboardViewAction.AddRecord -> {
                selectedAccount()?.let {
                    updateViewEvent(DashboardViewEvent.AddRecord(it.name, it.id.toLong()))
                }
            }
            is DashboardViewAction.OpenRecord -> {
                updateViewEvent(DashboardViewEvent.OpenRecord(recordId = viewAction.recordId))
            }
            is DashboardViewAction.SelectAccount -> {
                currentViewState().accounts.map {
                    if (it.id != viewAction.accountId) it.copy(isSelected = false)
                    else {
                        getRecords(it)
                        it.copy(isSelected = true)
                    }
                }.also {
                    accountsRef.set(it)
                    updateViewState(currentViewState().copy(accounts = it))
                }
            }
            DashboardViewAction.AddAccount -> updateViewEvent(DashboardViewEvent.AddAccount)
        }
    }

    private fun getRecords(account: AccountUiModel) = viewModelScope.launch(Dispatchers.IO) {
        getRecordsUseCase.getRecords(account.id.toLong())
            .collect {
                it.map {
                    RecordUiModel(
                        id = it.id.toInt(),
                        text = it.category.name,
                        amount = it.amount.toString(),
                        account = account,
                        time = "Today",
                        category = CategoryUiModel(
                            id = it.category.id.toInt(),
                            name = it.category.name,
                            imageUrl = it.category.imageUrl
                        )
                    )
                }.also {
                    updateViewState(
                        currentViewState().copy(
                            records = it,
                            isRecordsEmpty = it.isEmpty()
                        )
                    )
                }
            }
    }

    var recordsAmountsJobs = SupervisorJob() + Dispatchers.IO
    val recordsScope = CoroutineScope(recordsAmountsJobs)
    val accountsRef = AtomicReference<List<AccountUiModel>>()
    private fun getAccounts() = viewModelScope.launch(Dispatchers.IO) {
        getAccountsUseCase().collect { accounts ->
            recordsAmountsJobs.cancelChildren()
            val selected =
                currentViewState().accounts.find { account -> account.isSelected }
            val newAccounts =
                accountPreviewUiMapper.mapList(accounts)
                    .mapIndexed { index, account ->
                        if ((selected == null && index == 0) || account.id == selected?.id) {
                            account.copy(isSelected = true)
                        } else {
                            account.copy(isSelected = false)
                        }
                    }
            accountsRef.set(newAccounts)
            recordsScope.launch(Dispatchers.Main) {
                val observable = accounts.map { account ->
                    getRecordsAmountUseCase.execute(accountId = account.id)
                        .map {
                            val newAccount = account.copy(remaining = account.amount + it)
                            if(newAccount.remaining < newAccount.amount /2 ){
                                updateViewEvent(DashboardViewEvent.ShowWarning)
                            }
                            accountsRef.set(accountsRef.get().map {
                                if (it.id.toLong() == account.id) {
                                    it.copy(remaining = newAccount.remaining)
                                } else {
                                    it
                                }
                            })
                            newAccount
                        }
                }.let { Observable.merge(it) }
                observable.subscribe { acc ->
                    val selected =
                        accountsRef.get().find { account -> account.isSelected }

                    val newList = accountsRef.get()
                        .mapIndexed { index, account ->
                            if ((selected == null && index == 0) || account.id == selected?.id) {
                                account.copy(isSelected = true)
                            } else {
                                account.copy(isSelected = false)
                            }
                        }
                    updateViewState(
                        currentViewState().copy(accounts = newList, isAccountsEmpty = false)
                    )
                }.also { compositeDisposable.add(it) }
            }
        }
    }

    private fun selectedAccount() = currentViewState().accounts.find { it.isSelected }

    init {
        getAccounts()
    }
}