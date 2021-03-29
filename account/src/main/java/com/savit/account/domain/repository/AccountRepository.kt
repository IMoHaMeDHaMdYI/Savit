package com.savit.account.domain.repository

import com.savit.account.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccounts(): Flow<List<Account>>
    suspend fun getAccountById(id: Int): Account
    suspend fun addAccount(account: Account)
    suspend fun deleteAccount(account: Account)
    suspend fun updateAccount(account: Account)

}
