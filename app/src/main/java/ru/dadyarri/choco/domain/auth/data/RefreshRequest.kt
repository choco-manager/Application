package ru.dadyarri.choco.domain.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(
    val refreshToken: String,
)
