package com.obriysoft.test_task_for_eatster.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SlideEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun slidesDao(): SlidesDao
}