package ru.dadyarri.choco.domain.auth.data

data class RestorationTokenResponse(
    val restorationToken: String,
    val login: String,
)
