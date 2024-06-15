package ru.dadyarri.choco.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.dadyarri.choco.ui.components.common.BottomBar
import ru.dadyarri.choco.ui.components.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    title: String,
    navController: NavHostController,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    canGoBack: Boolean = false,
    goBack: () -> Boolean = navController::navigateUp,
    showBottomBar: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
    fabIcon: ImageVector? = null,
    fabAction: () -> Unit = {},
    children: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                canGoBack = canGoBack,
                goBack = goBack,
                title = title,
                actions = actions
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        },
        floatingActionButton = {
            if (fabIcon != null) {
                FloatingActionButton(
                    onClick = fabAction
                ) {
                    Icon(imageVector = fabIcon, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(28.dp)
            ) {
                PullToRefreshBox(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh
                ) {
                    children()
                }
            }
        }
    }
}