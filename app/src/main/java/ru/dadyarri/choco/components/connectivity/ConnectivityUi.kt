package ru.dadyarri.choco.components.connectivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import ru.dadyarri.choco.R
import ru.dadyarri.choco.connectivity.ConnectionState
import ru.dadyarri.choco.connectivity.connectivityState
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ConnectivityUi() {
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    var isVisible by remember { mutableStateOf(isConnected) }

    if (isConnected) {
        if (isVisible) {
            Row(
                Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.connectivity_avalable),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        LaunchedEffect(Unit) {
            delay(3.seconds)
            isVisible = false
        }

    } else {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.error)
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.connectivity_unavailable),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onError
            )
        }
        isVisible = true
    }
}