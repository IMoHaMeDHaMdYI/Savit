package com.savit.record.di

import com.savit.record.data.repository.RecordRepositoryImpl
import com.savit.record.domain.repsotory.RecordRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRecordRepository(bind: RecordRepositoryImpl): RecordRepository
}
