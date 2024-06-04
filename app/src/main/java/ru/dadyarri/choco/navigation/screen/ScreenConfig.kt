package ru.dadyarri.choco.navigation.screen

data class ScreenConfig(
    val showBottomBar: Boolean,
    val position: ScreenPosition
) {
    companion object {
        fun default(): ScreenConfig {
            return ScreenConfig(
                showBottomBar = false,
                position = ScreenPosition.None
            )
        }
    }
}
