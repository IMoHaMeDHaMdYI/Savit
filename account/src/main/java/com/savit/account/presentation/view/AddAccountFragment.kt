package com.savit.account.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.savit.account.R
import com.savit.account.databinding.FragmentAddAccountBinding
import com.savit.account.presentation.viewmodel.AddAccountViewModel
import com.savit.account.presentation.viewstate.AddAccountViewAction
import com.savit.account.presentation.viewstate.AddAccountViewEvent
import com.savit.account.presentation.viewstate.AddAccountViewState
import com.savit.core.base.view.BaseFragment
import com.savit.core.extension.viewModelWithProvider
import javax.inject.Inject

class AddAccountFragment @Inject constructor(val factory: AddAccountViewModel.Factory) :
    BaseFragment<
            AddAccountViewState,
            AddAccountViewEvent,
            AddAccountViewAction,
            AddAccountViewModel,
            FragmentAddAccountBinding>() {

    override val viewModel: AddAccountViewModel by viewModelWithProvider {
        factory.create()
    }

    override val theme: Int = R.style.Savit

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddAccountBinding {
        return FragmentAddAccountBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountEditText.doOnTextChanged { text, _, _, _ ->
            postAction(AddAccountViewAction.UpdateName(text.toString()))
        }
        binding.amountEditText.doOnTextChanged { text, _, _, _ ->
            postAction(AddAccountViewAction.UpdateAmount(text.toString()))
        }
        binding.backImageView.postClickAction(AddAccountViewAction.Cancel)
        binding.doneImageView.postClickAction(AddAccountViewAction.AddAccount)
    }

    override fun renderViewState(viewState: AddAccountViewState) {
        binding.amountEditText.error = if (viewState.showAmountMissing) {
            "Enter the amount."
        } else {
            null
        }
        binding.accountEditText.error = if (viewState.showNameMissing) {
            "Enter the name."
        } else {
            null
        }
    }

    override fun renderViewEvent(viewEvent: AddAccountViewEvent) {
        when (viewEvent) {
            AddAccountViewEvent.Added -> {
                findNavController().popBackStack()
            }
            AddAccountViewEvent.Cancel -> {
                showCancelDialog()
            }
            AddAccountViewEvent.Back -> {
                findNavController().popBackStack()
            }
        }
    }

    private fun showCancelDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you sure you want to cancel and discard the input?")
            .setPositiveButton(
                "Yes"
            ) { dialog, _ ->
                dialog.dismiss()
                postAction(AddAccountViewAction.Back)
            }.setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}