package com.savit.dashboard.presentation.viewmodel

import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.model.AccountUiModel
import com.savit.dashboard.presentation.model.CategoryUiModel
import com.savit.dashboard.presentation.model.RecordUiModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState
import kotlin.random.Random

class DashboardViewModel :
    BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {
    private val accounts = listOf(
        AccountUiModel(id = 12, "Cash", "EGP 333", true),
        AccountUiModel(id = 132, "Credit", "EGP 33223", false),
        AccountUiModel(id = 125, "Debit", "EGP 3343", false),
    )
    override val initViewState: DashboardViewState = DashboardViewState(
        accounts, listOf(
            RecordUiModel(
                id = Random.nextInt(), text = "Koshary", amount = "-EGP 325",
                category = CategoryUiModel(
                    name = "Shopping",
                    imageUrl = "https://image.flaticon.com/icons/png/512/630/630746.png",
                    id = Random.nextInt()
                ),
                account = accounts.first(),
                time = "Today"
            ),
            RecordUiModel(
                id = Random.nextInt(), text = "Snacks", amount = "-EGP 22",
                category = CategoryUiModel(
                    name = "Shopping",
                    imageUrl = "https://image.flaticon.com/icons/png/512/630/630746.png",
                    id = Random.nextInt()
                ),
                account = accounts.first(),
                time = "Today"
            ),
            RecordUiModel(
                id = Random.nextInt(), text = "Salary", amount = "EGP 5000",
                category = CategoryUiModel(
                    name = "Income",
                    imageUrl = "https://image.flaticon.com/icons/png/512/1466/1466703.png",
                    id = Random.nextInt()
                ),
                account = accounts.first(),
                time = "Yesterday"
            ),
            RecordUiModel(
                id = Random.nextInt(), text = "Rent", amount = "-EGP 4000",
                category = CategoryUiModel(
                    name = "Rent",
                    imageUrl = "https://www.pngrepo.com/png/128622/512/for-rent.png",
                    id = Random.nextInt()
                ),
                account = accounts.first(),
                time = "Yesterday"
            ),
        )
    ).also(::updateViewState)

    override fun postAction(viewAction: DashboardViewAction) {
        when (viewAction) {
            DashboardViewAction.AddRecord -> {
                updateViewEvent(DashboardViewEvent.AddRecord)
            }
            is DashboardViewAction.OpenRecord -> {
                updateViewEvent(DashboardViewEvent.OpenRecord(recordId = viewAction.recordId))
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