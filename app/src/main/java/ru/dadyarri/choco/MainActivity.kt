package ru.dadyarri.choco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Domain
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.ModeNight
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.dadyarri.choco.core.model.data.DarkThemeConfig
import ru.dadyarri.choco.core.model.data.ServerConfig
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
            var themeDialogShowed by remember { mutableStateOf(false) }
            var serverDialogShowed by remember { mutableStateOf(false) }
            var darkThemeSelected by remember { mutableStateOf(prefs.darkThemeConfig == DarkThemeConfig.DARK) }
            var lightThemeSelected by remember { mutableStateOf(prefs.darkThemeConfig == DarkThemeConfig.LIGHT) }
            var systemThemeSelected by remember { mutableStateOf(prefs.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM) }
            var productionServerSelected by remember { mutableStateOf(prefs.serverConfig == ServerConfig.PRODUCTION) }
            var stageServerSelected by remember { mutableStateOf(prefs.serverConfig == ServerConfig.STAGE) }

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
                }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    themeDialogShowed = true
                                }),
                                headlineText = { Text(text = "Тема приложения") },
                                trailingContent = {
                                    Icon(
                                        imageVector = Icons.Rounded.ChevronRight,
                                        contentDescription = null
                                    )
                                })
                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    serverDialogShowed = true
                                }),
                                headlineText = { Text(text = "Сервер") },
                                trailingContent = {
                                    Icon(
                                        imageVector = Icons.Rounded.ChevronRight,
                                        contentDescription = null
                                    )
                                })
                        }
                    }
                }
                if (themeDialogShowed) {
                    AlertDialog(
                        onDismissRequest = { themeDialogShowed = false },
                        title = {
                            Text(
                                text = "Настройки темы",
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        text = {
                            Column {
                                ListItem(
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Rounded.ModeNight,
                                            contentDescription = null
                                        )
                                    },
                                    headlineText = { Text(text = "Тёмная") },
                                    trailingContent = {
                                        RadioButton(
                                            selected = darkThemeSelected,
                                            onClick = {
                                                darkThemeSelected = true
                                                lightThemeSelected = false
                                                systemThemeSelected = false
                                                viewModel.updateDarkThemeConfig(DarkThemeConfig.DARK)
                                            })
                                    }
                                )
                                ListItem(
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Rounded.LightMode,
                                            contentDescription = null
                                        )
                                    },
                                    headlineText = { Text(text = "Светлая") },
                                    trailingContent = {
                                        RadioButton(
                                            selected = lightThemeSelected,
                                            onClick = {
                                                lightThemeSelected = true
                                                darkThemeSelected = false
                                                systemThemeSelected = false
                                                viewModel.updateDarkThemeConfig(DarkThemeConfig.LIGHT)
                                            })
                                    }
                                )
                                ListItem(
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Rounded.Settings,
                                            contentDescription = null
                                        )
                                    },
                                    headlineText = { Text(text = "Как в системе") },
                                    trailingContent = {
                                        RadioButton(
                                            selected = systemThemeSelected,
                                            onClick = {
                                                systemThemeSelected = true
                                                darkThemeSelected = false
                                                lightThemeSelected = false
                                                viewModel.updateDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM)
                                            })
                                    }
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { themeDialogShowed = false }) {
                                Text(text = "Закрыть")
                            }
                        })
                }
                if (serverDialogShowed) {
                    AlertDialog(onDismissRequest = { serverDialogShowed = false }, title = {
                        Text(
                            text = "Настройки сервера"
                        )
                    }, text = {
                        Column {
                            ListItem(
                                leadingContent = {
                                    Icon(
                                        imageVector = Icons.Rounded.Domain,
                                        contentDescription = null
                                    )
                                },
                                headlineText = { Text(text = "Основной") },
                                trailingContent = {
                                    RadioButton(
                                        selected = productionServerSelected,
                                        onClick = {
                                            productionServerSelected = true
                                            stageServerSelected = false
                                            viewModel.updateServerConfig(ServerConfig.PRODUCTION)
                                        })
                                })
                            ListItem(
                                leadingContent = { Icon(Icons.Rounded.BugReport, null) },
                                headlineText = { Text(text = "Отладочный") },
                                trailingContent = {
                                    RadioButton(
                                        selected = stageServerSelected,
                                        onClick = {
                                            productionServerSelected = false
                                            stageServerSelected = true
                                            viewModel.updateServerConfig(ServerConfig.STAGE)
                                        })
                                })
                        }
                    }, confirmButton = {
                        TextButton(onClick = { serverDialogShowed = false }) {
                            Text(text = "Закрыть")
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChocoManagerTheme {
        Greeting("Android")
    }
}