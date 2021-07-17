package com.savit.account.presentation.viewstate

import com.savit.core.base.viewstate.ViewAction

sealed class AccountsListViewAction : ViewAction {

    data class Select(val id: Long) : AccountsListViewAction()
    object AddAccount : AccountsListViewAction()
    object Done : AccountsListViewAction()
}