package com.savit.record.domain.usecase

import com.savit.record.domain.model.Record
import com.savit.record.domain.repsotory.RecordRepository
import javax.inject.Inject

class AddRecordUseCase @Inject constructor(private val repo: RecordRepository) {

    suspend operator fun invoke(record: Record) {
        repo.addRecord(record)
    }
}
