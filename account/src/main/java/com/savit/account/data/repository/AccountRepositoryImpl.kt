package com.savit.account.data.repository

import com.savit.account.data.datasource.AccountDataSource
import com.savit.account.data.mapper.AccountMapper
import com.savit.account.domain.model.Account
import com.savit.account.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dataSource: AccountDataSource,
    private val mapper: AccountMapper
) : AccountRepository {

    override suspend fun getAccounts(): Flow<List<Account>> {
        return dataSource.getAccounts()
            .map { accounts -> mapper.mapList(accounts) }
    }

    override suspend fun getAccountById(id: Int): Account {
        return mapper.map(dataSource.getAccountById(id))
    }

    override suspend fun addAccount(account: Account) {
        dataSource.addAccount(mapper.reverse(account))
    }

    override suspend fun deleteAccount(account: Account) {
        dataSource.deleteAccount(mapper.reverse(account))
    }

    override suspend fun updateAccount(account: Account) {
        dataSource.updateAccount(mapper.reverse(account))
    }

}
