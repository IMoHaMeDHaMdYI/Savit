package com.savit.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.savit.local.model.AccountWithRecords
import com.savit.local.model.DBRecord

@Dao
interface RecordDao {

    @Query("SELECT * FROM records WHERE account = :accountId LIMIT :limit OFFSET :offset ")
    suspend fun getRecordsByAccount(accountId: Long, offset: Int, limit: Int): List<DBRecord>

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: Int): List<DBRecord>

    @Transaction
    @Query("SELECT * FROM accounts WHERE id = :accountId")
    suspend fun getAccountWithRecords(accountId: Long): List<AccountWithRecords>
}