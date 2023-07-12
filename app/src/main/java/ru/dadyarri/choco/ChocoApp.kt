package ru.dadyarri.choco

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import ru.dadyarri.choco.core.navigation.ChocoNavHost
import ru.dadyarri.choco.core.navigation.Screen
import ru.dadyarri.choco.pages.SettingsPage
import ru.dadyarri.choco.ui.ChocoAppState
import ru.dadyarri.choco.ui.rememberAppState
import ru.dadyarri.choco.util.INetworkMonitor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChocoApp(
    networkMonitor: INetworkMonitor,
    appState: ChocoAppState = rememberAppState(
        networkMonitor = networkMonitor
    )
) {

    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                "Нет подключения к интернету",
                duration = SnackbarDuration.Indefinite
            )
        }
    }

    Scaffold(
        topBar = {
            val currentDestination = appState.currentTopLevelDestination
            if (currentDestination != null) {
                CenterAlignedTopAppBar(title = { Text(text = stringResource(currentDestination.title)) })
            }
            ChocoNavHost(
                appState = appState,
                onShowSnackbar = { message, action ->
                    snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = action,
                        duration = SnackbarDuration.Short
                    ) == SnackbarResult.ActionPerformed
                },
                modifier = Modifier
            )
        },
        bottomBar = {
            ChocoBottomAppBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            SettingsPage()
        }
    }
}

@Composable
fun ChocoBottomAppBar(
    destinations: List<Screen>,
    onNavigateToDestination: (Screen) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {

    NavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestination(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { Icon(destination.icon, contentDescription = null) }
            )
        }
    }

}

private fun NavDestination?.isTopLevelDestination(destination: Screen) = this?.hierarchy?.any {
    it.route?.contains(destination.name, true) ?: false

} ?: false