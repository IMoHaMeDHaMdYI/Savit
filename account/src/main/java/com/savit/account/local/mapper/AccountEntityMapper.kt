package com.savit.account.local.mapper

import com.savit.account.data.model.AccountEntity
import com.savit.core.mapper.BiMapper
import com.savit.local.model.DBAccount
import javax.inject.Inject

class AccountEntityMapper @Inject constructor() : BiMapper<DBAccount, AccountEntity> {
    override fun reverse(output: AccountEntity): DBAccount {
        return DBAccount(id = output.id, name = output.name, amount = output.amount)
    }

    override fun map(input: DBAccount): AccountEntity {
        return AccountEntity(id = input.id, name = input.name, amount = input.amount)
    }
}
