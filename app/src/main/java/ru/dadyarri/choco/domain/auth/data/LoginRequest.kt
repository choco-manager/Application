package ru.dadyarri.choco.domain.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login: String,
    val password: String,
    val fcmToken: String,
)
