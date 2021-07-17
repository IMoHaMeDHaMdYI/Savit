package com.savit.dashboard.presentation.mapper

import com.savit.account.domain.model.Account
import com.savit.core.mapper.Mapper
import com.savit.dashboard.presentation.model.AccountUiModel
import javax.inject.Inject

class AccountPreviewUiMapper @Inject constructor() : Mapper<Account, AccountUiModel> {
    override fun map(input: Account): AccountUiModel {
        return AccountUiModel(
            id = input.id.toInt(),
            name = input.name,
            amount = input.amount.toString(),
            isSelected = false,
            remaining = input.remaining
        )
    }
}