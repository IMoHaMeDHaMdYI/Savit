package com.savit.dashboard.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.savit.core.base.view.BaseFragment
import com.savit.core.extension.viewModelWithProvider
import com.savit.dashboard.R
import com.savit.dashboard.databinding.FragmentDashboardBinding
import com.savit.dashboard.presentation.view.adapter.AccountsAdapter
import com.savit.dashboard.presentation.view.adapter.PlanOverViewAdapter
import com.savit.dashboard.presentation.view.adapter.RecordsAdapter
import com.savit.dashboard.presentation.viewmodel.DashboardViewModel
import com.savit.dashboard.presentation.viewstate.DashboardViewAction
import com.savit.dashboard.presentation.viewstate.DashboardViewEvent
import com.savit.dashboard.presentation.viewstate.DashboardViewState
import com.savit.record.presentation.view.AddRecordFragmentArgs
import javax.inject.Inject
import javax.inject.Provider

class DashboardFragment @Inject constructor(viewModelProvider: Provider<DashboardViewModel>) :
    BaseFragment<
            DashboardViewState,
            DashboardViewEvent,
            DashboardViewAction,
            DashboardViewModel,
            FragmentDashboardBinding
            >() {
    override val viewModel: DashboardViewModel by viewModelWithProvider {
        viewModelProvider.get()
    }
    override val theme: Int = R.style.Savit

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    private val accountsAdapter = AccountsAdapter {
        postAction(DashboardViewAction.SelectAccount(it))
    }

    private val planOverview = PlanOverViewAdapter()
    private val recordsAdapter = RecordsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountsRV.adapter = accountsAdapter
        binding.recordRecyclerView.adapter = recordsAdapter
        binding.addAccountButton.postClickAction(DashboardViewAction.AddAccount)
        binding.fabAddRecord.postClickAction(DashboardViewAction.AddRecord)
        binding.planOverviewRecyclerView.adapter = planOverview
    }

    override fun renderViewState(viewState: DashboardViewState) {
        accountsAdapter.submitList(viewState.accounts)
        recordsAdapter.submitList(viewState.records)
        planOverview.submitList(viewState.planOverViewItems)
        binding.accountsRV.isVisible = !viewState.isAccountsEmpty
        binding.recordsCardView.isVisible = !viewState.isRecordsEmpty
    }

    override fun renderViewEvent(viewEvent: DashboardViewEvent) {
        when (viewEvent) {
            is DashboardViewEvent.AddRecord -> {
                val args = AddRecordFragmentArgs(
                    accountName = viewEvent.accountName,
                    accountId = viewEvent.accountId
                )
                findNavController().navigate(R.id.addRecordFragment, args.toBundle())
            }
            is DashboardViewEvent.OpenRecord -> {

            }
            DashboardViewEvent.AddAccount -> findNavController().navigate(R.id.addAccountFragment)
            DashboardViewEvent.ShowWarning -> AlertDialog.Builder(requireContext())
                .setTitle("Only half of the initial amount is remaining! be careful or be poor.")
                .setPositiveButton("Roger that") { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }
}