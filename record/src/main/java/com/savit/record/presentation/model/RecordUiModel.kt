package com.savit.record.presentation.model

import com.savit.category.model.Category

data class RecordUiModel(
    val id: Long,
    val name: String,
    val category: Category,
    val amount: String,
    val accountName: String,
    val date: String
)
