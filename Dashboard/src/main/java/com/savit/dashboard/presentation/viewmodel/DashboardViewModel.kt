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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class DashboardViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val accountPreviewUiMapper: AccountPreviewUiMapper,
    private val getRecordsUseCase: GetRecordUseCase
) : BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {
    private val accounts = listOf<AccountUiModel>()

    private val records = listOf(
        RecordUiModel(
            id = Random.nextInt(), text = "Koshary", amount = "-EGP 325",
            category = CategoryUiModel(
                name = "Shopping",
                imageUrl = "https://image.flaticon.com/icons/png/512/630/630746.png",
                id = Random.nextInt()
            ),
            account = AccountUiModel(
                id = 2,
                name = "Salary",
                amount = "231",
                isSelected = false
            ),
            time = "Today"
        ),
        RecordUiModel(
            id = Random.nextInt(), text = "Snacks", amount = "-EGP 22",
            category = CategoryUiModel(
                name = "Shopping",
                imageUrl = "https://image.flaticon.com/icons/png/512/630/630746.png",
                id = Random.nextInt()
            ),
            account = AccountUiModel(
                id = 2,
                name = "Salary",
                amount = "231",
                isSelected = false
            ),
            time = "Today"
        ),
        RecordUiModel(
            id = Random.nextInt(), text = "Salary", amount = "EGP 5000",
            category = CategoryUiModel(
                name = "Income",
                imageUrl = "https://image.flaticon.com/icons/png/512/1466/1466703.png",
                id = Random.nextInt()
            ),
            account = AccountUiModel(
                id = 2,
                name = "Salary",
                amount = "231",
                isSelected = false
            ),
            time = "Yesterday"
        ),
        RecordUiModel(
            id = Random.nextInt(), text = "Rent", amount = "-EGP 4000",
            category = CategoryUiModel(
                name = "Rent",
                imageUrl = "https://www.pngrepo.com/png/128622/512/for-rent.png",
                id = Random.nextInt()
            ),
            account = AccountUiModel(
                id = 2,
                name = "Salary",
                amount = "231",
                isSelected = false
            ),
            time = "Yesterday"
        ),
    )
    override val initViewState: DashboardViewState = DashboardViewState(
        accounts = accounts, records = records, isAccountsEmpty = true, isRecordsEmpty = false
    ).also(::updateViewState)

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
                }.also { updateViewState(currentViewState().copy(accounts = it)) }
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

    private fun getAccounts() = viewModelScope.launch(Dispatchers.IO) {
        getAccountsUseCase().collect { accounts ->
            launch(Dispatchers.Main) {
                val selected = currentViewState().accounts.find { account -> account.isSelected }
                val newAccounts =
                    accountPreviewUiMapper.mapList(accounts).mapIndexed { index, account ->
                        if ((selected == null && index == 0) || account.id == selected?.id) {
                            account.copy(isSelected = true)
                        } else {
                            account.copy(isSelected = false)
                        }
                    }
                updateViewState(
                    currentViewState().copy(accounts = newAccounts, isAccountsEmpty = false)
                )
            }
        }
    }

    private fun selectedAccount() = currentViewState().accounts.find { it.isSelected }

    init {
        getAccounts()
    }
}