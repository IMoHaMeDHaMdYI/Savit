package com.savit.record.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.savit.core.base.view.BaseFragment
import com.savit.core.extension.viewModelWithProvider
import com.savit.record.R
import com.savit.record.databinding.FragmentAddRecordBinding
import com.savit.record.presentation.viewmodel.AddRecordViewModel
import com.savit.record.presentation.viewstate.AddRecordViewAction
import com.savit.record.presentation.viewstate.AddRecordViewEvent
import com.savit.record.presentation.viewstate.AddRecordViewState
import javax.inject.Inject

class AddRecordFragment @Inject constructor(viewModelFactory: AddRecordViewModel.Factory) :
    BaseFragment<AddRecordViewState, AddRecordViewEvent, AddRecordViewAction, AddRecordViewModel, FragmentAddRecordBinding>() {
    private val args: AddRecordFragmentArgs by navArgs()
    override val viewModel: AddRecordViewModel by viewModelWithProvider {
        viewModelFactory.create(accountId = args.accountId, accName = args.accountName)
    }
    override val theme: Int = R.style.Savit

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddRecordBinding {
        return FragmentAddRecordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.amountEditText.doOnTextChanged { text, start, count, after ->
            if (binding.currencyTextView.text == getString(R.string.egp))
                postAction(AddRecordViewAction.UpdateAmount(text.toString()))
            else
                postAction(
                    AddRecordViewAction.UpdateAmount(
                        (text.toString().toDouble() * -1).toString()
                    )
                )

        }
        binding.backImageView.postClickAction(AddRecordViewAction.Back)
        binding.doneImageView.postClickAction(AddRecordViewAction.Done)
        binding.accountCard.postClickAction(AddRecordViewAction.OpenAccounts)
        parentFragmentManager.setFragmentResultListener(
            "account",
            viewLifecycleOwner
        ) { key, result ->
            val name = result.getString("name") ?: ""
            val id = result.getLong("id")
            postAction(AddRecordViewAction.UpdateAccount(name = name, id = id))
        }

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.text == getString(R.string.income)) {
                    binding.currencyTextView.text = getString(R.string.egp)
                } else {
                    binding.currencyTextView.text = getString(R.string.negative_egp)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun renderViewState(viewState: AddRecordViewState) {
        binding.categoryTextView.text = "Shopping"
        binding.accountTextView.text = viewState.accountName
    }

    override fun renderViewEvent(viewEvent: AddRecordViewEvent) {
        when (viewEvent) {
            AddRecordViewEvent.Back -> findNavController().popBackStack()
            AddRecordViewEvent.Cancel -> findNavController().popBackStack()
            AddRecordViewEvent.Done -> findNavController().popBackStack()
            AddRecordViewEvent.OpenRecord -> navigateActivity(0)
        }
    }
}