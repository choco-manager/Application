package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.viewmodels.RequestPasswordRestorationViewModel

@Composable
fun RequestPasswordRestorationScreenRoot(navController: NavHostController) {
    val viewModel: RequestPasswordRestorationViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(
        title = state.title.asString(),
        navController = navController,
        showBottomBar = false,
        canGoBack = navController.previousBackStackEntry != null,
        verticalArrangement = Arrangement.Center,
        isRefreshing = false,
        onRefresh = {},
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        RequestPasswordRestorationScreen(state = state, onAction = viewModel::onAction)
    }

}