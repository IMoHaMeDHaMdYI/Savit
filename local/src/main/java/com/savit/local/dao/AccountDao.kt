package com.savit.local.dao

import androidx.room.*
import com.savit.local.model.DBAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    /**
     * 1- get account by id
     * 2- get accounts
     * 3- get account with records
     *
     *  account + List<Record>
     */

    @Query("SELECT * FROM accounts where id=:id")
    suspend fun getAccountById(id: Long): List<DBAccount>

    @Query("SELECT * FROM accounts")
    fun getAccounts(): Flow<List<DBAccount>>

    @Insert
    suspend fun addAccount(account: DBAccount)

    @Delete
    suspend fun deleteAccount(account: DBAccount)

    @Update
    suspend fun updateAccount(account: DBAccount)
}

