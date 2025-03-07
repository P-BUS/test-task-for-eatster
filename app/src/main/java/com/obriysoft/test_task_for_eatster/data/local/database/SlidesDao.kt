package com.obriysoft.test_task_for_eatster.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SlidesDao {
    @Query("SELECT * FROM slides_database ORDER BY position ASC")
    fun getSlidesStream(): Flow<List<SlideEntity>>

    @Upsert
    suspend fun insertSlides(slides: List<SlideEntity>)

    @Query("DELETE FROM slides_database")
    suspend fun deleteAllSlides()
}