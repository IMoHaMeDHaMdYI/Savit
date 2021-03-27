package com.savit.account.presentation.view

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
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

class AddAccountFragment @Inject constructor() :
    BaseFragment<
            AddAccountViewState,
            AddAccountViewEvent,
            AddAccountViewAction,
            AddAccountViewModel,
            FragmentAddAccountBinding>() {

    @Inject
    lateinit var factory: AddAccountViewModel.Factory
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

    override fun renderViewState(viewState: AddAccountViewState) {
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