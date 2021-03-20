package com.savit.local.di

import com.savit.local.dao.AccountDao
import com.savit.local.dao.CategoryDao
import com.savit.local.dao.RecordDao
import com.savit.local.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
object DaoModule {

    @Provides
    fun provideAccountDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }

    @Provides
    fun provideRecordDao(database: AppDatabase): RecordDao {
        return database.recordDao()
    }

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }
}
