package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.common.UiText

data class LoginState(
    val isRefreshing: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isFormEnabled: Boolean = true,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null
)
