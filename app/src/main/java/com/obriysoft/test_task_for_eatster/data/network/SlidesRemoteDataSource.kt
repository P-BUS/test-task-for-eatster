package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.SlidesResponse

interface SlidesRemoteDataSource {
    suspend fun getSlides(): ApiResult<SlidesResponse>
}