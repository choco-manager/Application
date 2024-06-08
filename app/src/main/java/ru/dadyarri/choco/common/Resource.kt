package ru.dadyarri.choco.common

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class SuccessNoData<T> : Resource<T>()
    class Error<T>(message: UiText, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}