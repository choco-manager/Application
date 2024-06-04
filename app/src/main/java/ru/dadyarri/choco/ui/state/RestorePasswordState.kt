package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText

data class RestorePasswordState(
    val title: UiText = UiText.StringResource(R.string.restore_password_screen),
    val isPasswordVisible: Boolean = false,
    val isFormEnabled: Boolean = true,
    val password: String = "",
    val passwordError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null
)