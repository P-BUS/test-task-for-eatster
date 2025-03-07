package com.obriysoft.test_task_for_eatster.data.local.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesLocalDataSource @Inject constructor(
    private val database: SlidesDao
) {
    fun getAllSlides(): Flow<List<SlidesEntity>> =
        database.getAllSlides()

    suspend fun insertAll(fact: SlidesEntity) =
        database.insertAll(fact)
}