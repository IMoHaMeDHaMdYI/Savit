package com.savit.account.data.datasource

import android.accounts.Account
import com.savit.account.data.model.AccountEntity
import kotlinx.coroutines.flow.Flow

interface AccountDataSource {

    suspend fun getAccounts(): Flow<List<AccountEntity>>
    suspend fun getAccountById(id: Int): AccountEntity
    suspend fun addAccount(account: AccountEntity)
    suspend fun deleteAccount(account: AccountEntity)
    suspend fun updateAccount(account: AccountEntity)

}
