package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.SlidesResponse
import retrofit2.Response
import retrofit2.http.GET

interface SlidesApiService {

    @GET(ENDPOINT)
    suspend fun getSlides(): Response<SlidesResponse>

    companion object {
        const val ENDPOINT = "kiosks/slides?language=en"
    }
}