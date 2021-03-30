package com.savit.record.di

import com.savit.record.data.datasource.RecordLocalDataSource
import com.savit.record.local.datasource.RecordLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindRecordLocalDataSource(bind: RecordLocalDataSourceImpl): RecordLocalDataSource
}