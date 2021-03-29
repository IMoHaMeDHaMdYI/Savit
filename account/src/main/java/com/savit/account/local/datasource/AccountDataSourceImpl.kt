package com.savit.account.local.datasource

import com.savit.account.data.datasource.AccountDataSource
import com.savit.account.data.model.AccountEntity
import com.savit.account.local.mapper.AccountEntityMapper
import com.savit.local.dao.AccountDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountDataSourceImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val mapper: AccountEntityMapper
) : AccountDataSource {
    override suspend fun getAccounts(): Flow<List<AccountEntity>> {
        return accountDao.getAccounts()
            .map { accounts -> mapper.mapList(accounts) }
    }

    override suspend fun getAccountById(id: Int): AccountEntity {
        return accountDao.getAccountById(id = id.toLong())
            .map { accounts -> mapper.map(accounts) }
            .first()
    }

    override suspend fun addAccount(account: AccountEntity) {
        accountDao.addAccount(mapper.reverse(account))
    }

    override suspend fun deleteAccount(account: AccountEntity) {
        accountDao.deleteAccount(mapper.reverse(account))
    }

    override suspend fun updateAccount(account: AccountEntity) {
        accountDao.updateAccount(mapper.reverse(account))
    }
}