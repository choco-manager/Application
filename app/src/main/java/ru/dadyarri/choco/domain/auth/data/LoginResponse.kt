package ru.dadyarri.choco.domain.auth.data

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
