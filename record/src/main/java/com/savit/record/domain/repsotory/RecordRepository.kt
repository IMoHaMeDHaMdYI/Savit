package com.savit.record.domain.repsotory

import com.savit.record.domain.model.Record
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getRecords(accountId: Long): Observable<List<Record>>
    suspend fun addRecord(record: Record)
    fun getRecordAmount(accountId: Long): Observable<Int>

}
