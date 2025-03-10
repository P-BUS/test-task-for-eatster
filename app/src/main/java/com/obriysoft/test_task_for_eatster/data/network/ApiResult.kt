package com.example.testtaskfore.data.network

sealed class ApiResult<T : Any> {
    data class Success<T : Any>(val data: T) : ApiResult<T>()
    data class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()
}