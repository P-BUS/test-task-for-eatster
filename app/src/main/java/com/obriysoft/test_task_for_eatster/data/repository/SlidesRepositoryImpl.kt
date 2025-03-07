package com.example.testtaskfore.data.repository

import android.util.Log
import com.example.testtaskfore.data.network.ApiResult
import com.example.testtaskfore.data.network.SlidesRemoteDataSource
import com.obriysoft.test_task_for_eatster.data.local.database.SlidesLocalDataSource
import com.obriysoft.test_task_for_eatster.data.model.mapper.toDatabaseModel
import com.obriysoft.test_task_for_eatster.domain.model.Slide
import com.obriysoft.test_task_for_eatster.domain.model.mapper.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "SlidesRepository"

class SlidesRepositoryImpl @Inject constructor(
    private val database: SlidesLocalDataSource,
    private val network: SlidesRemoteDataSource
) : SlidesRepository {
    override val slidesStream: Flow<List<Slide>> =
        database.getSlidesStream()
            .map { it.toDomainModel() }

    override suspend fun updateSlidesDatabase() {
        withContext(Dispatchers.IO) {
            when (val response = network.getSlides()) {
                is ApiResult.Success -> {
                    val slidesList = response.data.toDatabaseModel()
                    //database.deleteAllSlides()
                    database.insertSlides(slidesList)
                }
                // Normally error goes to UI
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                else -> {}
            }
        }
    }
}