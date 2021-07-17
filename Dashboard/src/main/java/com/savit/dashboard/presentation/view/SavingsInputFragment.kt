package com.savit.dashboard.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.savit.core.base.view.BaseFragment
import com.savit.dashboard.databinding.FragmentSavingsInputBinding
import com.savit.dashboard.presentation.viewmodel.SavingsInputViewModel
import com.savit.dashboard.presentation.viewstate.SavingsInputViewAction
import com.savit.dashboard.presentation.viewstate.SavingsInputViewEvent
import com.savit.dashboard.presentation.viewstate.SavingsInputViewState

class SavingsInputFragment() : BaseFragment<
        SavingsInputViewState,
        SavingsInputViewEvent,
        SavingsInputViewAction,
        SavingsInputViewModel,
        FragmentSavingsInputBinding
        >() {

    override val viewModel: SavingsInputViewModel
        get() = TODO("Not yet implemented")
    override val theme: Int
        get() = TODO("Not yet implemented")

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSavingsInputBinding {
        TODO("Not yet implemented")
    }

    override fun renderViewState(viewState: SavingsInputViewState) {
        TODO("Not yet implemented")
    }

    override fun renderViewEvent(viewEvent: SavingsInputViewEvent) {
        TODO("Not yet implemented")
    }
}
