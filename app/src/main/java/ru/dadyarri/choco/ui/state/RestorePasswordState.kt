package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.navigation.screen.ScreenConfig
import ru.dadyarri.choco.navigation.screen.ScreenPosition

data class RestorePasswordState(
    val title: UiText = UiText.StringResource(R.string.restore_password_screen),
    val screenConfig: ScreenConfig = ScreenConfig(
        showBottomBar = false,
        position = ScreenPosition.None
    )
)
