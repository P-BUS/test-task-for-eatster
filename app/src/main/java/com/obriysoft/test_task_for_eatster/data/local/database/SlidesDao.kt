package com.obriysoft.test_task_for_eatster.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SlidesDao {
    @Query("SELECT * FROM images_database")
    fun getAllSlides(): Flow<List<SlidesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(image: SlidesEntity)

    @Query("DELETE FROM images_database")
    suspend fun deleteAll()
}