package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import ru.dadyarri.choco.ui.actions.RequestPasswordRestorationAction
import ru.dadyarri.choco.ui.components.Greeting
import ru.dadyarri.choco.ui.state.RequestPasswordRestorationState

@Composable
fun RequestPasswordRestorationScreen(
    state: RequestPasswordRestorationState,
    onAction: (RequestPasswordRestorationAction) -> Unit
) {
    Greeting(name = "Restore password")
}