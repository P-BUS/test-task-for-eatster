package com.example.testtaskfore.data.network

import retrofit2.Response

suspend fun <T : Any> handleApiResponse(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.code(), message = response.message())
        }
    } catch (exception: Throwable) {
        ApiResult.Error(code = exception.hashCode(), message = exception.message)
    }
}