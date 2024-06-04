package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import ru.dadyarri.choco.ui.actions.RestorePasswordAction
import ru.dadyarri.choco.ui.components.Greeting
import ru.dadyarri.choco.ui.state.RestorePasswordState
import kotlin.reflect.KFunction1

@Composable
fun RestorePasswordScreen(state: RestorePasswordState, onAction: KFunction1<RestorePasswordAction, Unit>) {
    Greeting(name = "Restore password")
}