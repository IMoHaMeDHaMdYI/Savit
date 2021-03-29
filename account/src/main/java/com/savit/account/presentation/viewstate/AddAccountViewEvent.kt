package com.savit.account.presentation.viewstate

import com.savit.core.base.viewstate.ViewEvent

sealed class AddAccountViewEvent : ViewEvent {

    object Cancel : AddAccountViewEvent()

    object Added : AddAccountViewEvent()

    object Back : AddAccountViewEvent()

}
