package com.savit.account.data.mapper

import com.savit.account.data.model.AccountEntity
import com.savit.account.domain.model.Account
import com.savit.core.mapper.BiMapper
import javax.inject.Inject

class AccountMapper @Inject constructor() : BiMapper<AccountEntity, Account> {

    override fun reverse(output: Account): AccountEntity {
        return AccountEntity(id = output.id, name = output.name, amount = output.amount)
    }

    override fun map(input: AccountEntity): Account {
        return Account(id = input.id, name = input.name, amount = input.amount)
    }

}
