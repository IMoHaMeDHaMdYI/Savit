package com.savit.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class DBRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ForeignKey(
        entity = DBCategory::class,
        parentColumns = ["id"],
        childColumns = ["category"]
    )
    val category: Long,
    val amount: Double,
    @ForeignKey(
        entity = DBAccount::class,
        parentColumns = ["id"],
        childColumns = ["account"]
    )
    val account: Long,
    val notes: String
)
