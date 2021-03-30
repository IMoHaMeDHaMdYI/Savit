package com.savit.core.base.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.core.event.EventObserver
import com.savit.core.extension.getThemeColor
import com.savit.core.base.viewstate.ViewAction
import com.savit.core.base.viewstate.ViewEvent
import com.savit.core.base.viewstate.ViewState

abstract class BaseFragment<
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction,
        VM : BaseViewModel<VS, VE, VA>,
        VB : ViewBinding
        > : Fragment() {

    protected abstract val viewModel: VM

    @get:StyleRes
    protected abstract val theme: Int

    private var _binding: VB? = null

    protected val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // create ContextThemeWrapper from the original Activity Context with the custom theme.
        val contextThemeWrapper: Context = ContextThemeWrapper(requireActivity(), theme)

        // clone the inflater using the ContextThemeWrapper.
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        val color = contextThemeWrapper.getThemeColor(android.R.attr.colorBackground)
        requireActivity().window.setBackgroundDrawable(ColorDrawable(color))

        _binding = onCreateBinding(localInflater, container)
        return binding.root
    }

    abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, { renderViewState(it) })
        viewModel.viewEvent.observe(viewLifecycleOwner, EventObserver { renderViewEvent(it) })
    }

    abstract fun renderViewState(viewState: VS)

    abstract fun renderViewEvent(viewEvent: VE)

    protected fun postAction(action: VA) {
        viewModel.postAction(action)
    }

    fun View.postClickAction(action: VA) {
        setOnClickListener { postAction(action) }
    }

    fun View.postClickAction(action: () -> VA) {
        setOnClickListener { postAction(action.invoke()) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateActivity(id: Int) {
        (requireActivity() as MainNavigator).navigateAccountsList(id)
    }
}
