package com.savit.record.presentation.viewstate

import com.savit.core.base.viewstate.ViewEvent

sealed class AddRecordViewEvent : ViewEvent {

    object Done : AddRecordViewEvent()
    object Back : AddRecordViewEvent()
    object Cancel : AddRecordViewEvent()

    object OpenRecord : AddRecordViewEvent()
}