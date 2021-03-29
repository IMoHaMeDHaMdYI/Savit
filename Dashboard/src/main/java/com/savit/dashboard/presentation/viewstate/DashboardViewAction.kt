package com.savit.dashboard.presentation.viewstate

import com.savit.core.base.viewstate.ViewAction

sealed class DashboardViewAction : ViewAction {

    class SelectAccount(val accountId: Int) : DashboardViewAction()

    object AddRecord : DashboardViewAction()
    object AddAccount : DashboardViewAction()

    data class OpenRecord(val recordId: Int) : DashboardViewAction()
}
