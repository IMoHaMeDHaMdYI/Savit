package com.savit.account.presentation.mapper

import com.savit.account.domain.model.Account
import com.savit.account.presentation.model.SelectableAccountUiModel
import com.savit.core.mapper.Mapper
import javax.inject.Inject

class SelectableAccountUiMapper @Inject constructor() : Mapper<Account, SelectableAccountUiModel> {
    override fun map(input: Account): SelectableAccountUiModel {
        return SelectableAccountUiModel(
            id = input.id,
            name = input.name,
            amount = input.amount.toString(),
            isSelected = false
        )
    }
}