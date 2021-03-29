package com.savit.core.di

import android.content.Context
import com.savit.local.dao.AccountDao
import com.savit.local.dao.CategoryDao
import com.savit.local.dao.RecordDao
import com.savit.local.db.AppDatabase
import com.savit.local.di.LocalModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [LocalModule::class])
@Singleton
interface CoreComponent {

    fun provideDatabase(): AppDatabase
    fun provideAccountDao(): AccountDao
    fun provideRecordDao(): RecordDao
    fun provideCategoryDao(): CategoryDao

    @Component.Factory
    fun interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
}

fun provideCoreComponent(context: Context): CoreComponent {
    return DaggerCoreComponent.factory().create(context)
}