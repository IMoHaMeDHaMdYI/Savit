package com.savit.record.local.mapper

import com.savit.category.model.categories
import com.savit.core.mapper.BiMapper
import com.savit.local.model.DBRecord
import com.savit.record.data.model.RecordEntity
import javax.inject.Inject

class RecordEntityMapper @Inject constructor() : BiMapper<DBRecord, RecordEntity> {
    override fun map(input: DBRecord): RecordEntity {
        return RecordEntity(
            id = input.id,
            name = input.notes,
            category = categories.find { it.id == input.category }?: categories[0],
            accountId = input.account,
            amount = input.amount
        )
    }

    override fun reverse(output: RecordEntity): DBRecord {
        return DBRecord(
            id = output.id,
            notes = output.name,
            category = output.category.id,
            account = output.accountId,
            amount = output.amount
        )
    }
}