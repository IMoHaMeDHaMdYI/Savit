package com.savit.dashboard.presentation.viewstate

import com.savit.core.base.viewstate.ViewState
import com.savit.dashboard.presentation.model.AccountUiModel
import com.savit.dashboard.presentation.model.RecordUiModel

data class DashboardViewState(
    val accounts: List<AccountUiModel>,
    val records: List<RecordUiModel>
) : ViewState