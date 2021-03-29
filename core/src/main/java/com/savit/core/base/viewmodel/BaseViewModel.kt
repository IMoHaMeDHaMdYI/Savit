package com.savit.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savit.core.base.viewstate.ViewAction
import com.savit.core.base.viewstate.ViewEvent
import com.savit.core.base.viewstate.ViewState
import com.savit.core.event.Event

abstract class BaseViewModel<VS : ViewState, VE : ViewEvent, VA : ViewAction> : ViewModel() {

    abstract val initViewState: VS

    private val _viewState = MutableLiveData<VS>()
    val viewState: LiveData<VS> get() = _viewState

    private val _viewEvent = MutableLiveData<Event<VE>>()
    val viewEvent: LiveData<Event<VE>> get() = _viewEvent

    abstract fun postAction(viewAction: VA)

    fun currentViewState(): VS {
        return viewState.value ?: initViewState
    }

    protected fun updateViewState(state: VS) {
        _viewState.value = state
    }


    protected fun updateViewEvent(event: VE) {
        _viewEvent.value = Event(event)
    }
}
