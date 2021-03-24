package com.savit.local.di

import android.content.Context
import androidx.room.Room
import com.savit.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideDatabase(context: Context): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
        return builder.build()
    }
}