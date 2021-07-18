package com.savit.record.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.savit.category.model.Category
import com.savit.category.model.categories
import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.record.domain.model.Record
import com.savit.record.domain.usecase.AddRecordUseCase
import com.savit.record.presentation.viewstate.AddRecordViewAction
import com.savit.record.presentation.viewstate.AddRecordViewEvent
import com.savit.record.presentation.viewstate.AddRecordViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecordViewModel @AssistedInject constructor(
    @Assisted accountId: Long,
    @Assisted accName: String,
    private val addRecordUseCase: AddRecordUseCase
) : BaseViewModel<AddRecordViewState, AddRecordViewEvent, AddRecordViewAction>() {
    private var amount: Double? = null

    private var name: String = ""
    var category: Category? = categories[0]
    private var account: Long = accountId
    private var accountName: String = accName

    override val initViewState: AddRecordViewState = AddRecordViewState(
        categoryName = category?.name ?: "",
        accountName = accountName
    ).also(::updateViewState)


    override fun postAction(viewAction: AddRecordViewAction) {
        when (viewAction) {
            AddRecordViewAction.Back -> updateViewEvent(AddRecordViewEvent.Back)
            AddRecordViewAction.Cancel -> if (isReady()) {
                updateViewEvent(AddRecordViewEvent.Cancel)
            } else {
                updateViewEvent(AddRecordViewEvent.Back)
            }
            AddRecordViewAction.Done -> if (isReady()) {
                viewModelScope.launch(Dispatchers.IO) {
                    addRecordUseCase(
                        Record(id = 0, category!!, name, amount!!, account)
                    )
                }
                updateViewEvent(AddRecordViewEvent.Done)
            }
            is AddRecordViewAction.UpdateAccount -> {
                account = viewAction.id
                accountName = viewAction.name
                updateViewState(currentViewState().copy(accountName = accountName))
            }
            is AddRecordViewAction.UpdateAmount -> amount = viewAction.value.toDoubleOrNull()
            is AddRecordViewAction.UpdateCategory -> {
                category = viewAction.value
                updateViewState(currentViewState().copy(categoryName = category?.name ?: ""))
            }
            is AddRecordViewAction.UpdateName -> name = viewAction.value
            AddRecordViewAction.OpenAccounts -> updateViewEvent(AddRecordViewEvent.OpenRecord)
        }
    }

    fun setCategoryState(cat: Category) {
        category = cat
        updateViewState(currentViewState().copy(categoryName = cat.name))
    }

    private fun isReady() = amount != null && category != null

    @AssistedFactory
    fun interface Factory {
        fun create(accountId: Long, accName: String): AddRecordViewModel
    }
}