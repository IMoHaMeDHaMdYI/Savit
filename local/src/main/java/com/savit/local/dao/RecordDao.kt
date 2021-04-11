package com.savit.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.savit.local.model.AccountWithRecords
import com.savit.local.model.DBRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM records WHERE account = :accountId ")
    fun getRecordsByAccount(accountId: Long): Flow<List<DBRecord>>

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: Int): List<DBRecord>

    @Transaction
    @Query("SELECT * FROM accounts WHERE id = :accountId")
    suspend fun getAccountWithRecords(accountId: Long): List<AccountWithRecords>

    @Insert
    suspend fun addRecord(record: DBRecord)
}