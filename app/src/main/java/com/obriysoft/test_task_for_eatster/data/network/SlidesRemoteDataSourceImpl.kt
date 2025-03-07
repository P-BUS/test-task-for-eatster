package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.SlidesResponse
import javax.inject.Inject

class SlidesRemoteDataSourceImpl @Inject constructor(
    private val slidesApiService: SlidesApiService
) : SlidesRemoteDataSource {
    override suspend fun getSlides(): ApiResult<SlidesResponse> =
        handleApiResponse {
            slidesApiService.getSlides()
        }
}