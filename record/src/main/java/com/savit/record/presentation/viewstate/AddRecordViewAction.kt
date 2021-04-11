package com.savit.record.presentation.viewstate

import com.savit.category.model.Category
import com.savit.core.base.viewstate.ViewAction

sealed class AddRecordViewAction : ViewAction {

    data class UpdateName(val value: String) : AddRecordViewAction()
    data class UpdateCategory(val value: Category) : AddRecordViewAction()
    data class UpdateAccount(val id: Long, val name: String) : AddRecordViewAction()
    data class UpdateAmount(val value: String) : AddRecordViewAction()

    object OpenAccounts : AddRecordViewAction()

    object Done : AddRecordViewAction()
    object Back : AddRecordViewAction()
    object Cancel : AddRecordViewAction()
}
