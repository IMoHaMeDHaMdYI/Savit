package com.savit.account.di

import com.savit.account.data.datasource.AccountDataSource
import com.savit.account.local.datasource.AccountDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
internal interface DataSourceModule {

    @Binds
    fun bindAccountDataSourceImpl(bind: AccountDataSourceImpl): AccountDataSource

}
