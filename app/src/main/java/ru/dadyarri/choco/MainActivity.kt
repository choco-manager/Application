package ru.dadyarri.choco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler
import ru.dadyarri.choco.ui.screens.ChocoApp
import ru.dadyarri.choco.ui.theme.ChocoManagerTheme
import ru.dadyarri.choco.viewmodels.SplashScreenViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var snackbarMessageHandler: SnackbarMessageHandler

    @Inject
    lateinit var navigationHandler: NavigationHandler

    private val viewModel by viewModels<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

            setKeepOnScreenCondition {
                viewModel.state.value.isVisible
            }
        }
        enableEdgeToEdge()
        setContent {
            ChocoManagerTheme {
                ChocoApp(snackbarMessageHandler, navigationHandler)
            }
        }
    }
}