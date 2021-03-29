package com.savit.account.di

import com.savit.account.data.repository.AccountRepositoryImpl
import com.savit.account.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoryModule {

    @Binds
    fun bindRepositoryImpl(bind: AccountRepositoryImpl): AccountRepository

}
