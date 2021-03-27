package com.savit.dashboard.presentation.model

data class RecordUiModel(
    val id: Int,
    val text: String,
    val amount: String,
    val category: CategoryUiModel,
    val account: AccountUiModel,
    val time: String
)
