package com.savit.record.domain.model

import com.savit.category.model.Category

data class Record(
    val id: Long,
    val category: Category,
    val name: String,
    val amount: Double,
    val accountId: Long
)
