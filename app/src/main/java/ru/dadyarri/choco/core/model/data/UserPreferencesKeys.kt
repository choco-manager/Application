package ru.dadyarri.choco.core.model.data

data class UserPreferences(
    val darkThemeConfig: DarkThemeConfig,
    val serverConfig: ServerConfig,
    val showWholesalePrices: Boolean
)
