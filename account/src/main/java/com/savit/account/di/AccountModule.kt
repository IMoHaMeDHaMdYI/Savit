package com.savit.account.di

import dagger.Module

@Module(includes = [DataSourceModule::class, RepositoryModule::class, FragmentModule::class])
interface AccountModule