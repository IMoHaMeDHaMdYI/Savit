package com.savit.account.presentation.model

data class SelectableAccountUiModel(
    val id: Long,
    val name: String,
    val amount: String,
    val isSelected: Boolean
)
