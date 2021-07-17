package com.savit.dashboard.presentation.viewstate

import com.savit.core.base.viewstate.ViewEvent

sealed class DashboardViewEvent : ViewEvent {

    class OpenRecord(val recordId: Int) : DashboardViewEvent()

    data class AddRecord(val accountName: String, val accountId: Long) : DashboardViewEvent()

    object AddAccount : DashboardViewEvent()

    object ShowWarning : DashboardViewEvent()
}