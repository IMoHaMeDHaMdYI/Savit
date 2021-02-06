package com.savit.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class DBCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val icon: String // url or path
){


    fun a (){
        DBAC
    }
}