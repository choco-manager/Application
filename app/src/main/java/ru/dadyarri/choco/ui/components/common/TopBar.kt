package ru.dadyarri.choco.ui.components.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dadyarri.choco.system.network.ConnectivityObserver
import ru.dadyarri.choco.viewmodels.TopBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    canGoBack: Boolean,
    goBack: () -> Boolean,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Column {
        TopAppBar(
            title = {
                Text(title, style = MaterialTheme.typography.titleLarge)
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                if (canGoBack) {
                    IconButton(onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = actions
        )
        if (!LocalInspectionMode.current) {
            val viewModel: TopBarViewModel = hiltViewModel()
            val networkState by
            viewModel.networkState.collectAsState(ConnectivityObserver.Status.Available)

            AnimatedVisibility(
                visible = networkState != ConnectivityObserver.Status.Available,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            ) {
                NoInternetAvailable()
            }
        }
    }
}