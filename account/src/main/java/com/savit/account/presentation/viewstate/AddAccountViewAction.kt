package com.savit.account.presentation.viewstate

import com.savit.core.base.viewstate.ViewAction

sealed class AddAccountViewAction : ViewAction {

    data class AddAccount(val name: String, val amount: String) : AddAccountViewAction()

    object Cancel : AddAccountViewAction()
    object Back : AddAccountViewAction()

}
