package com.savit.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class AccountWithRecords(
    @Embedded val account: DBAccount,
    @Relation(parentColumn = "id", entityColumn = "id")
    val records: List<DBRecord>
)
