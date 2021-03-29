package com.savit.account.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.savit.account.R
import com.savit.account.databinding.FragmentAccountsListBinding
import com.savit.account.presentation.view.adapter.SelectAccountsAdapter
import com.savit.account.presentation.viewmodel.AccountListViewModel
import com.savit.account.presentation.viewstate.AccountsListViewAction
import com.savit.account.presentation.viewstate.AccountsListViewEvent
import com.savit.account.presentation.viewstate.AccountsListViewState
import com.savit.core.base.view.BaseFragment
import com.savit.core.extension.viewModelWithProvider
import javax.inject.Inject
import javax.inject.Provider

class AccountsListFragment @Inject constructor(provider: Provider<AccountListViewModel>) :
    BaseFragment<
            AccountsListViewState,
            AccountsListViewEvent,
            AccountsListViewAction,
            AccountListViewModel,
            FragmentAccountsListBinding
            >() {
    override val viewModel: AccountListViewModel by viewModelWithProvider {
        provider.get()
    }

    override val theme: Int = R.style.Savit

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountsListBinding {
        return FragmentAccountsListBinding.inflate(inflater, container, false)
    }

    private val accountsAdapter = SelectAccountsAdapter {
        postAction(AccountsListViewAction.Select(id = it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectAccountRecyclerView.adapter = accountsAdapter
    }

    override fun renderViewState(viewState: AccountsListViewState) {
        accountsAdapter.submitList(viewState.accounts)
    }

    override fun renderViewEvent(viewEvent: AccountsListViewEvent) {
        when (viewEvent) {
            AccountsListViewEvent.Back -> requireActivity().onBackPressed()
            is AccountsListViewEvent.Done -> {
                setFragmentResult("account", bundleOf("account" to viewEvent.account))
                requireActivity().onBackPressed()
            }
        }
    }
}