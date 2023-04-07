package com.billysoft.domain.model.exceptions

class ApiException : Exception()

class NetworkException : Exception()

class UnknownException(message: String?) : Exception(message)

enum class ExceptionCause {

    API_ERROR, NETWORK_ERROR, UNKNOWN_ERROR;

    companion object {
        fun fromException(exception: Throwable) = when (exception) {
            is ApiException -> API_ERROR
            is NetworkException -> NETWORK_ERROR
            else -> UNKNOWN_ERROR
        }
    }

}