package com.obriysoft.test_task_for_eatster.data.local.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SlidesLocalDataSourceImpl @Inject constructor(
    private val database: SlidesDao
) : SlidesLocalDataSource {
    override fun getSlidesStream(): Flow<List<SlideEntity>> =
        database.getSlidesStream()

    override suspend fun insertSlides(slides: List<SlideEntity>) =
        database.insertSlides(slides)

    override suspend fun deleteAllSlides() =
        database.deleteAllSlides()
}