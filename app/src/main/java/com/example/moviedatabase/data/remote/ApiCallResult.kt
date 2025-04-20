package com.example.moviedatabase.data.remote

sealed class ApiCallResult<out T> {
    data class Success<T>(val data: T) : ApiCallResult<T>()
    data class Error(val exception: Throwable) : ApiCallResult<Nothing>()
}