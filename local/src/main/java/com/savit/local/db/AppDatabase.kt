package com.savit.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.savit.local.dao.AccountDao
import com.savit.local.dao.CategoryDao
import com.savit.local.dao.RecordDao
import com.savit.local.model.DBAccount
import com.savit.local.model.DBCategory
import com.savit.local.model.DBRecord

@Database(entities = [DBAccount::class, DBCategory::class, DBRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun recordDao(): RecordDao
    abstract fun categoryDao(): CategoryDao

    fun instance(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
