package com.savit.record.data.datasource

import com.savit.record.data.model.RecordEntity
import kotlinx.coroutines.flow.Flow

interface RecordLocalDataSource {
    fun getRecords(accountId: Long): Flow<List<RecordEntity>>
    suspend fun addRecord(record: RecordEntity)
}