package ru.dadyarri.choco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler
import ru.dadyarri.choco.ui.screens.ChocoApp
import ru.dadyarri.choco.ui.theme.ChocoManagerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var snackbarMessageHandler: SnackbarMessageHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChocoManagerTheme {
                ChocoApp(snackbarMessageHandler)
            }
        }
    }
}