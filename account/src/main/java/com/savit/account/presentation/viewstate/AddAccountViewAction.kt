package com.savit.account.presentation.viewstate

import com.savit.core.base.viewstate.ViewAction

sealed class AddAccountViewAction : ViewAction {

    object AddAccount : AddAccountViewAction()

    object Cancel : AddAccountViewAction()
    object Back : AddAccountViewAction()

    data class UpdateName(val value: String) : AddAccountViewAction()
    data class UpdateAmount(val value: String) : AddAccountViewAction()

}
