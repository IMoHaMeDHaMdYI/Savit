package com.savit.record.domain.repsotory

import com.savit.record.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getRecords(accountId: Long): Flow<List<Record>>
    suspend fun addRecord(record: Record)
}
