package com.example.testtaskfore.data.database

import android.content.Context
import androidx.room.Room
import com.obriysoft.test_task_for_eatster.data.local.database.AppDatabase
import com.obriysoft.test_task_for_eatster.data.local.database.SlidesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "slides_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DaoModule {
        @Provides
        fun provideSlidesDao(database: AppDatabase): SlidesDao {
            return database.slidesDao()
        }
    }
}