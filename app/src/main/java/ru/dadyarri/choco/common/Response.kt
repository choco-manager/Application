package ru.dadyarri.choco.common

sealed class Response<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T) : Response<T>(data)
    class Error<T>(message: UiText, data: T? = null) : Response<T>(data, message)
    class Loading<T>(data: T? = null) : Response<T>(data)
}