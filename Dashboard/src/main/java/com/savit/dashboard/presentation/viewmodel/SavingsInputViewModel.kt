package com.savit.dashboard.presentation.viewmodel

import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.viewstate.SavingsInputViewAction
import com.savit.dashboard.presentation.viewstate.SavingsInputViewEvent
import com.savit.dashboard.presentation.viewstate.SavingsInputViewState
import kotlin.math.sqrt


class SavingsInputViewModel : BaseViewModel<
        SavingsInputViewState,
        SavingsInputViewEvent,
        SavingsInputViewAction
        >() {
    override val initViewState: SavingsInputViewState = SavingsInputViewState()

    override fun postAction(viewAction: SavingsInputViewAction) {

    }

    private val x = 343.0


}