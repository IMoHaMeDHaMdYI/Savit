package com.savit.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class DBAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val amount: Double
)
