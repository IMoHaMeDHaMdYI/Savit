package com.savit.account.presentation.viewstate

import com.savit.account.domain.model.Account
import com.savit.core.base.viewstate.ViewEvent

sealed class AccountsListViewEvent : ViewEvent {

    data class Done(val account: Account) : AccountsListViewEvent()

    object AddAccount : AccountsListViewEvent()

    object Back : AccountsListViewEvent()
}
