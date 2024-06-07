package ru.dadyarri.choco.domain.auth.data

data class RestorePasswordRequest(
    val restorationToken: String,
    val newPassword: String,
)
