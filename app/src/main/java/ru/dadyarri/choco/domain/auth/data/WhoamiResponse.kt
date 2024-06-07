package ru.dadyarri.choco.domain.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class WhoamiResponse(
    val login: String,
    val name: String,
)
