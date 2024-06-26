package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        isRefreshing = false,
        onRefresh = {},
    ) {
        RequestPasswordRestorationScreen(state = state, onAction = viewModel::onAction)
    }

}