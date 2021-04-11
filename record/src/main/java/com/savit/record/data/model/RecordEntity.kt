package com.savit.record.data.model

import com.savit.category.model.Category

data class RecordEntity(
    val id: Long,
    val category: Category,
    val name: String,
    val amount: Double,
    val accountId: Long
)
