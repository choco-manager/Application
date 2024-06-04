package ru.dadyarri.choco.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    children: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing,
                        delayMillis = 500
                    )
                )
                .padding(innerPadding)
        ) {
//        if (!LocalInspectionMode.current) {
//            val viewModel: TopBarViewModel = hiltViewModel()
//            val networkState by
//            viewModel.networkState.collectAsState(ConnectivityObserver.Status.Available)
//
//            AnimatedVisibility(
//                visible = networkState != ConnectivityObserver.Status.Available,
//                enter = fadeIn() + slideInVertically(
//                    initialOffsetY = { fullHeight -> -fullHeight },
//                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
//                ),
//                exit = fadeOut() + slideOutVertically(
//                    targetOffsetY = { fullHeight -> -fullHeight },
//                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
//                )
//            ) {
//                NoInternetAvailable()
//            }
//        }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(28.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = horizontalAlignment,
                    verticalArrangement = verticalArrangement
                ) {
                    children()
                }
            }
        }
    }
}