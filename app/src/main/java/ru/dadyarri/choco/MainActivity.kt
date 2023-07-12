package ru.dadyarri.choco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.dadyarri.choco.core.model.data.DarkThemeConfig
import ru.dadyarri.choco.ui.theme.ChocoManagerTheme
import ru.dadyarri.choco.util.INetworkMonitor
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: INetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        val viewModel by viewModels<MainActivityViewModel>()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val prefs = viewModel.getPreferences().collectAsState().value

            ChocoManagerTheme(
                darkTheme = when (prefs.darkThemeConfig) {
                    DarkThemeConfig.DARK -> true
                    DarkThemeConfig.LIGHT -> false
                    DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
                }
            ) {
                ChocoApp(networkMonitor = networkMonitor)
            }
        }
    }
}
