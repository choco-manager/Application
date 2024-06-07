package ru.dadyarri.choco.storage

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val accessToken: String = "",
    val refreshToken: String = "",
)
