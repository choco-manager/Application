package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.navigation.screen.ScreenConfig
import ru.dadyarri.choco.navigation.screen.ScreenPosition

data class RestorePasswordState(
    val screenConfig: ScreenConfig = ScreenConfig(
        showBottomBar = false,
        position = ScreenPosition.None
    )
)
