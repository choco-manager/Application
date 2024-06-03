package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler
import ru.dadyarri.choco.ui.components.Greeting

@Composable
fun ChocoApp(snackbarMessageHandler: SnackbarMessageHandler) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessageHandler) {
        snackbarMessageHandler.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(name = "Android", modifier = Modifier.padding(innerPadding))
    }
}