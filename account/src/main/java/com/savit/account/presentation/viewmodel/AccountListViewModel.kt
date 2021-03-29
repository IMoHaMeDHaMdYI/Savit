package com.savit.account.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.savit.account.domain.model.Account
import com.savit.account.domain.usecase.GetAccountsUseCase
import com.savit.account.presentation.mapper.SelectableAccountUiMapper
import com.savit.account.presentation.viewstate.AccountsListViewAction
import com.savit.account.presentation.viewstate.AccountsListViewEvent
import com.savit.account.presentation.viewstate.AccountsListViewState
import com.savit.core.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountListViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val selectableAccountUiMapper: SelectableAccountUiMapper
) : BaseViewModel<AccountsListViewState, AccountsListViewEvent, AccountsListViewAction>() {
    override val initViewState: AccountsListViewState = AccountsListViewState(accounts = listOf())

    private var selectedAccount: Account? = null
    override fun postAction(viewAction: AccountsListViewAction) {
        when (viewAction) {
            AccountsListViewAction.Done -> {
                if (selectedAccount != null)
                    updateViewEvent(AccountsListViewEvent.Done(account = selectedAccount!!))
            }
            is AccountsListViewAction.Select -> {
                accounts?.find { account -> account.id == viewAction.id }?.let { account ->
                    selectedAccount = account
                    updateViewState(
                        currentViewState().copy(accounts = currentViewState().accounts.map { selectableAccount ->
                            if (selectableAccount.id == account.id) {
                                selectableAccount.copy(isSelected = true)
                            } else {
                                selectableAccount.copy(isSelected = false)
                            }
                        })
                    )
                }
            }
        }
    }

    private var accounts: List<Account>? = null

    private fun getAccounts() = viewModelScope.launch(Dispatchers.IO) {
        getAccountsUseCase().collect { accounts ->
            launch(Dispatchers.Main) {
                val selected = currentViewState().accounts.find { account -> account.isSelected }
                val newAccounts =
                    selectableAccountUiMapper.mapList(accounts).mapIndexed { index, account ->
                        if ((selected == null && index == 0) || account.id == selected?.id) {
                            account.copy(isSelected = true)
                        } else {
                            account.copy(isSelected = false)
                        }
                    }
                this@AccountListViewModel.accounts = accounts
                updateViewState(
                    currentViewState().copy(accounts = newAccounts)
                )
            }
        }
    }

    init {
        getAccounts()
    }
}