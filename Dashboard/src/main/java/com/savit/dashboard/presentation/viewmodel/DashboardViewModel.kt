package com.savit.dashboard.presentation.viewmodel

import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState

class DashboardViewModel :
    BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {
    override val initViewState: DashboardViewState = DashboardViewState(listOf(), listOf())

    override fun postAction(viewAction: DashboardViewAction) {
        TODO("Not yet implemented")
    }
}