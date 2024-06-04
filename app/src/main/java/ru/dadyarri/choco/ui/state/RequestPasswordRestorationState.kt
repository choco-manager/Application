package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText

data class RequestPasswordRestorationState(
    val title: UiText = UiText.StringResource(R.string.restore_password_screen),
    val isFormEnabled: Boolean = true,
    val login: String = "",
    val loginError: UiText? = null,
)
