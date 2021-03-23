package com.savit.dashboard.presentation.viewstate

import com.savit.core.base.viewstate.ViewEvent

sealed class DashboardViewEvent : ViewEvent {

    class OpenRecord(val recordId: Long) : DashboardViewEvent()

    object AddRecord : DashboardViewEvent()
}