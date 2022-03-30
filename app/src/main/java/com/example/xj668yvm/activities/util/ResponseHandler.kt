package com.example.xj668yvm.activities.util

sealed class ResponseHandler<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResponseHandler<T>(data)
    class Error<T>( data: T) : ResponseHandler<T>(data)
    class Loading<T> : ResponseHandler<T>()
}