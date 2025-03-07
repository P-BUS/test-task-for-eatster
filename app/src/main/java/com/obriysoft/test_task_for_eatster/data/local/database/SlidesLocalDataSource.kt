package com.obriysoft.test_task_for_eatster.data.local.database

import kotlinx.coroutines.flow.Flow

interface SlidesLocalDataSource {
    fun getSlidesStream(): Flow<List<SlideEntity>>
    suspend fun insertSlides(slides: List<SlideEntity>)
    suspend fun deleteAllSlides()
}