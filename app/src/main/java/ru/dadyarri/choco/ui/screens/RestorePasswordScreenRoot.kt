package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.viewmodels.RestorePasswordViewModel

@Composable
fun RestorePasswordScreenRoot(navController: NavHostController) {

    val viewModel: RestorePasswordViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(
        title = state.title.asString(),
        navController = navController,
        showBottomBar = false,
        isRefreshing = false,
        onRefresh = {},
    ) {
        RestorePasswordScreen(state = state, onAction = viewModel::onAction)
    }
}