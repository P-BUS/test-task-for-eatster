package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.SlidesResponse
import javax.inject.Inject

class SlidesRemoteDataSource @Inject constructor(
    private val slidesApiService: SlidesApiService
) {
    suspend fun getSlides(): ApiResult<SlidesResponse> =
        handleApiResponse {
            slidesApiService.getSlides()
        }
}