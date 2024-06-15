package ru.dadyarri.choco.ui.components.common.pulltorefresh

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PullRefreshBox(refreshing: Boolean, onRefresh: () -> Unit, content: @Composable () -> Unit) {
    val state = rememberPullRefreshState(refreshing = refreshing, onRefresh = onRefresh)

    Box(modifier = Modifier.pullRefresh(state)) {
        content()

        PullRefreshIndicator(
            refreshing = refreshing,
            state = state,
            Modifier.align(Alignment.TopCenter)
        )
    }
}