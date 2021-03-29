package com.savit.core.extension

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner


fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}

fun Fragment.hideKeyboard(view: View) {
    val inputMethodManager =
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showKeyboard(editText: EditText) {
    requireContext().showKeyboard(editText)
}

/**
 * The ViewModelStoreOwner controls the scope of the ViewModel.
 * It may be overridden with a different ViewModelStoreOwner,
 * such as the host Activity or the parent fragment, in order to
 * scope the lifetime of the ViewModel to the lifetime of the
 * ViewModelStoreOwner that is passed in.
 * https://desmondtzq.com/2019/10/25/fragment-factory-dagger/
 */
inline fun <reified T : ViewModel> Fragment.viewModelWithProvider(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline provider: () -> T
) = viewModels<T>(ownerProducer) {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return provider.invoke() as T
        }
    }
}

inline fun <reified T : ViewModel> Fragment.savedStateViewModelWithProvider(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline savedStateRegistryOwnerProducer: () -> SavedStateRegistryOwner = { this },
    defaultArgs: Bundle? = null,
    crossinline provider: (SavedStateHandle) -> T
) = viewModels<T>(ownerProducer) {
    object : AbstractSavedStateViewModelFactory(savedStateRegistryOwnerProducer(), defaultArgs) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            @Suppress("UNCHECKED_CAST")
            return provider(handle) as T
        }
    }
}
