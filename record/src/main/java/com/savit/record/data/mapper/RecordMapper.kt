package com.savit.record.data.mapper

import com.savit.core.mapper.BiMapper
import com.savit.record.data.model.RecordEntity
import com.savit.record.domain.model.Record
import javax.inject.Inject

class RecordMapper @Inject constructor() : BiMapper<RecordEntity, Record> {
    override fun map(input: RecordEntity): Record {
        return Record(
            id = input.id,
            name = input.name,
            category = input.category,
            accountId = input.accountId,
            amount = input.amount
        )
    }

    override fun reverse(output: Record): RecordEntity {
        return RecordEntity(
            id = output.id,
            name = output.name,
            category = output.category,
            accountId = output.accountId,
            amount = output.amount
        )
    }
}
