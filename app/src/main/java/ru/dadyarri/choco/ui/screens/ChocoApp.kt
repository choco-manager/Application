package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.navigation.screen.ScreenConfig
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler
import ru.dadyarri.choco.ui.components.Greeting
import ru.dadyarri.choco.viewmodels.LoginViewModel
import ru.dadyarri.choco.viewmodels.RestorePasswordViewModel

@Composable
fun ChocoApp(
    snackbarMessageHandler: SnackbarMessageHandler,
    navigationHandler: NavigationHandler,
    navController: NavHostController = rememberNavController()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    var screenConfig by remember {
        mutableStateOf(ScreenConfig.default())
    }

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

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Login,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Login> {
                val vm: LoginViewModel = hiltViewModel()
                val st by vm.state.collectAsState()

                screenConfig = st.screenConfig

                LoginScreen(st, vm::onAction)
            }

            composable<Route.RestorePassword> {

                val vm: RestorePasswordViewModel = hiltViewModel()
                val st by vm.state.collectAsState()

                screenConfig = st.screenConfig

                BaseScreen {
                    Greeting(name = "Restore password")
                }
            }
        }
    }
}