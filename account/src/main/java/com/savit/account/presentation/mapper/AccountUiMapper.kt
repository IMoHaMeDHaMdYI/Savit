package com.savit.account.presentation.mapper

import com.savit.account.domain.model.Account
import com.savit.account.presentation.model.AccountUiModel
import com.savit.core.mapper.Mapper
import javax.inject.Inject

class AccountUiMapper @Inject constructor() : Mapper<Account, AccountUiModel> {

    override fun map(input: Account): AccountUiModel {
        return AccountUiModel(name = input.name, amount = input.amount.toString())
    }

}
