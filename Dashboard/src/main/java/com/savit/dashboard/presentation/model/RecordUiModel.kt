package com.savit.dashboard.presentation.model

data class RecordUiModel(
    val text: String,
    val amount: Double,
    val category: CategoryUiModel,
    val accountId: Int,
    val time: String
)
