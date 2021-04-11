package com.savit.record.di

import dagger.Module

@Module(includes = [DataModule::class, FragmentModule::class, RepositoryModule::class])
class RecordModule {
}