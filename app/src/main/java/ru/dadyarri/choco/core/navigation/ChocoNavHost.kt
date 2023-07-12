package ru.dadyarri.choco.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dadyarri.choco.pages.SettingsPage
import ru.dadyarri.choco.ui.ChocoAppState

@Composable
fun ChocoNavHost(
    appState: ChocoAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier,
    startDestination: String = "home"
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("home") { Column {} }
        composable("orders") { Column {} }
        composable("shipments") { Column {} }
        composable("warehouse") { Column {} }
        composable("settings") { SettingsPage() }
    }
}