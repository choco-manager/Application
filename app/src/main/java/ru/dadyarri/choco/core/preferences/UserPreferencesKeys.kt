package ru.dadyarri.choco.core.preferences

data class UserPreferences(
    val darkThemeConfig: DarkThemeConfig,
    val serverConfig: ServerConfig,
    val showWholesalePrices: Boolean
)
