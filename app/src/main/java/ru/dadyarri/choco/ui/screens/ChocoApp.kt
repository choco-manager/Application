package ru.dadyarri.choco.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler

@Composable
fun ChocoApp(
    snackbarMessageHandler: SnackbarMessageHandler,
    navigationHandler: NavigationHandler,
    navController: NavHostController = rememberNavController()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessageHandler) {
        snackbarMessageHandler.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(navigationHandler) {
        navigationHandler.destination.collect { destination ->
            navController.navigate(destination)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.Login,
    ) {
        composable<Route.Login> {
            LoginScreenRoot(navController)
        }

        composable<Route.RequestPasswordRestoration> {
            RequestPasswordRestorationScreenRoot(navController)
        }
    }
}