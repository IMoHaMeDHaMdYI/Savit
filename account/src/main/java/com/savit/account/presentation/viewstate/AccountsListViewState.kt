package com.savit.account.presentation.viewstate

import com.savit.account.presentation.model.SelectableAccountUiModel
import com.savit.core.base.viewstate.ViewState

data class AccountsListViewState(val accounts: List<SelectableAccountUiModel>) : ViewState
