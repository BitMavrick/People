package com.bitmavrick.core.database.di

import android.content.Context
import androidx.room.Room
import com.bitmavrick.core.database.local.AppDatabase
import com.bitmavrick.core.database.local.dao.PeopleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    internal fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "people.db"
        ).build()
    }

    @Provides
    internal fun providePeopleDao(db: AppDatabase): PeopleDao = db.peopleDao()
}