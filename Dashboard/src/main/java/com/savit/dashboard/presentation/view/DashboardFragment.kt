package com.savit.dashboard.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.savit.core.base.view.BaseFragment
import com.savit.core.di.scopedComponent
import com.savit.dashboard.R
import com.savit.dashboard.databinding.FragmentDashboardBinding
import com.savit.dashboard.di.DaggerDashboardComponent
import com.savit.dashboard.di.DashboardComponent
import com.savit.dashboard.presentation.view.adapter.AccountsAdapter
import com.savit.dashboard.presentation.viewmodel.DashboardViewModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState

class DashboardFragment : BaseFragment<
        DashboardViewState,
        DashboardViewEvent,
        DashboardViewAction,
        DashboardViewModel,
        FragmentDashboardBinding
        >() {
    override val viewModel: DashboardViewModel by viewModels()
    override val theme: Int = R.style.Savit

    private val component: DashboardComponent by scopedComponent {
        DaggerDashboardComponent.factory()
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    private val accountsAdapter = AccountsAdapter {
        postAction(DashboardViewAction.SelectAccount(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountsRV.adapter = accountsAdapter
    }

    override fun renderViewState(viewState: DashboardViewState) {
        accountsAdapter.submitList(viewState.accounts)
    }

    override fun renderViewEvent(viewEvent: DashboardViewEvent) {
        when (viewEvent) {
            DashboardViewEvent.AddRecord -> {

            }
            is DashboardViewEvent.OpenRecord -> {

            }
        }
    }
}