package com.savit.record.local.datasource

import com.savit.local.dao.RecordDao
import com.savit.record.data.datasource.RecordLocalDataSource
import com.savit.record.data.model.RecordEntity
import com.savit.record.local.mapper.RecordEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordLocalDataSourceImpl @Inject constructor(
    private val dao: RecordDao,
    private val recordEntityMapper: RecordEntityMapper
) : RecordLocalDataSource {
    override fun getRecords(accountId: Long): Flow<List<RecordEntity>> {
        return dao.getRecordsByAccount(accountId = accountId).map {
            recordEntityMapper.mapList(it)
        }
    }

    override suspend fun addRecord(record: RecordEntity) {
        dao.addRecord(record = recordEntityMapper.reverse(record))
    }
}