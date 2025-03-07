package com.example.testtaskfore.data.repository

import com.obriysoft.test_task_for_eatster.domain.model.Slide
import kotlinx.coroutines.flow.Flow

interface SlidesRepository {
    val slidesStream: Flow<List<Slide>>
    suspend fun updateSlidesDatabase()
}