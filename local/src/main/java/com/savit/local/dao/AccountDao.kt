package com.savit.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.savit.local.model.DBAccount

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

    @Query("SELECT * FROM accounts LIMIT :limit OFFSET :offset ")
    suspend fun getAccounts(offset: Int, limit: Int): List<DBAccount>
}

