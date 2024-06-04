package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText

data class LoginState(
    val title: UiText = UiText.StringResource(R.string.login_screen),
    val isRefreshing: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isFormEnabled: Boolean = true,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null
)
