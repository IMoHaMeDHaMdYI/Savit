package com.savit.account.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.savit.account.domain.model.Account
import com.savit.account.domain.usecase.AddAccountUseCase
import com.savit.account.presentation.viewstate.AddAccountViewAction
import com.savit.account.presentation.viewstate.AddAccountViewEvent
import com.savit.account.presentation.viewstate.AddAccountViewState
import com.savit.core.base.viewmodel.BaseViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAccountViewModel @AssistedInject constructor(
    private val addAccountUseCase: AddAccountUseCase
) : BaseViewModel<AddAccountViewState, AddAccountViewEvent, AddAccountViewAction>() {

    override val initViewState: AddAccountViewState =
        AddAccountViewState().also { updateViewState(it) }

    override fun postAction(viewAction: AddAccountViewAction) {
        when (viewAction) {
            is AddAccountViewAction.AddAccount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    addAccountUseCase(
                        account = Account(
                            id = 0,
                            name = viewAction.name,
                            amount = viewAction.amount.toDouble()
                        )
                    )
                    launch(Dispatchers.Main) { updateViewEvent(AddAccountViewEvent.Added) }
                }
            }
            is AddAccountViewAction.Cancel -> {
                updateViewEvent(AddAccountViewEvent.Cancel)
            }
        }
    }

    @AssistedFactory
    fun interface Factory {
        fun create(): AddAccountViewModel
    }
}
