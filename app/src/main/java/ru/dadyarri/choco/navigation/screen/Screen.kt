package ru.dadyarri.choco.navigation.screen

enum class Screen(config: ScreenConfig) {
    Login(ScreenConfig(showBottomBar = false, canGoBack = false, position = ScreenPosition.None))
}