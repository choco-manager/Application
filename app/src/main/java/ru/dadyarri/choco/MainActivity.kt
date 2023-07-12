package ru.dadyarri.choco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.dadyarri.choco.core.model.data.DarkThemeConfig
import ru.dadyarri.choco.pages.SettingsPage
import ru.dadyarri.choco.ui.theme.ChocoManagerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
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
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(title = { Text(text = "ChocoManager") })
                },
                bottomBar = {
                    BottomAppBar {

                    }
                }) {
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
        }
    }
}
