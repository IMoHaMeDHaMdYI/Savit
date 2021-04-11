package com.savit.record.data.repository

import com.savit.record.data.datasource.RecordLocalDataSource
import com.savit.record.data.mapper.RecordMapper
import com.savit.record.domain.model.Record
import com.savit.record.domain.repsotory.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val dataSource: RecordLocalDataSource,
    private val mapper: RecordMapper
) : RecordRepository {
    override fun getRecords(accountId: Long): Flow<List<Record>> {
        return dataSource.getRecords(accountId).map {
            mapper.mapList(it)
        }
    }

    override suspend fun addRecord(record: Record) {
        dataSource.addRecord(mapper.reverse(record))
    }
}
