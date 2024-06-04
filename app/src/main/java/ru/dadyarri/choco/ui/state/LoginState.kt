package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.navigation.screen.ScreenConfig
import ru.dadyarri.choco.navigation.screen.ScreenPosition

data class LoginState(
    val isRefreshing: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isFormEnabled: Boolean = true,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val screenConfig: ScreenConfig = ScreenConfig(
        showBottomBar = false,
        position = ScreenPosition.None
    )
)
