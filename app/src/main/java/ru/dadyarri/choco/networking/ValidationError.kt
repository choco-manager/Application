package ru.dadyarri.choco.networking

import kotlinx.serialization.Serializable

@Serializable
data class ValidationError(
    val identifier: String,
    val errorMessage: String,
    val errorCode: String,
    val severity: ValidationErrorSeverity,
)

@Serializable
enum class ValidationErrorSeverity {
    Error,
    Warning,
    Info
}
