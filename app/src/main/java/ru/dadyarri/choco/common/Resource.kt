package ru.dadyarri.choco.common

import ru.dadyarri.choco.networking.ValidationError

sealed class Resource<T>(
    val data: T? = null,
    val message: UiText? = null,
    val validationErrors: List<ValidationError> = emptyList(),
) {
    class Success<T>(data: T) : Resource<T>(data)
    class SuccessNoData<T> : Resource<T>()
    class Error<T>(message: UiText, data: T? = null) : Resource<T>(data, message)
    class Forbidden<T>(message: UiText) : Resource<T>(null, message)
    class Unauthorized<T>(message: UiText) : Resource<T>(null, message)
    class BadRequest<T>(validationErrors: List<ValidationError>) :
        Resource<T>(validationErrors = validationErrors)

    class Loading<T> : Resource<T>()
}