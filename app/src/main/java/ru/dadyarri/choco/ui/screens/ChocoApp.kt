package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dadyarri.choco.ui.components.Greeting

@Composable
fun ChocoApp(

) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(name = "Hello", modifier = Modifier.padding(innerPadding))
    }
}