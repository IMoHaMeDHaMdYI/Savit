package com.savit.dashboard.presentation.viewmodel

import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.model.AccountUiModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState

class DashboardViewModel :
    BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {
    override val initViewState: DashboardViewState = DashboardViewState(
        listOf(
            AccountUiModel(id = 12, "Cash", "EGP 333", true),
            AccountUiModel(id = 132, "Credit", "EGP 33223", false),
            AccountUiModel(id = 125, "Debit", "EGP 3343", false),
        ), listOf()
    ).also(::updateViewState)

    override fun postAction(viewAction: DashboardViewAction) {
        when (viewAction) {
            DashboardViewAction.AddRecord -> {

            }
            is DashboardViewAction.OpenRecord -> {

            }
            is DashboardViewAction.SelectAccount -> {
                currentViewState().accounts.map {
                    if (it.id != viewAction.accountId) it.copy(isSelected = false)
                    else it.copy(isSelected = true)
                }.also { updateViewState(currentViewState().copy(accounts = it)) }
            }
        }
    }
}