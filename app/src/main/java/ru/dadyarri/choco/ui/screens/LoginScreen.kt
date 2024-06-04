package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dadyarri.choco.ui.components.Greeting

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    BaseScreen {
        Greeting(name = "Login")
    }
}