package com.savit.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class DBRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val category: Long,
    val amount: Double,
    val account: Long,
    val notes: String
)
