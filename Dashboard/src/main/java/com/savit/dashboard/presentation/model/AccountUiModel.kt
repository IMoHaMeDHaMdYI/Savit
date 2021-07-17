package com.savit.dashboard.presentation.model

data class AccountUiModel(
    val id: Int,
    val name: String,
    val amount: String,
    val isSelected: Boolean,
    val remaining: Double = 0.0
)
