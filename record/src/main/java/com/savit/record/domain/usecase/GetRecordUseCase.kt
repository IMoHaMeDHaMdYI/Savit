package com.savit.record.domain.usecase

import com.savit.record.domain.model.Record
import com.savit.record.domain.repsotory.RecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecordUseCase @Inject constructor(private val repository: RecordRepository) {


    fun getRecords(id: Long): Flow<List<Record>> {
        return repository.getRecords(accountId = id)
    }
}