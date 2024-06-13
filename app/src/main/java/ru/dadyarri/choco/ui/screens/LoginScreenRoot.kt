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
import ru.dadyarri.choco.ui.actions.LoginAction
import ru.dadyarri.choco.viewmodels.LoginViewModel

@Composable
fun LoginScreenRoot(
    navController: NavHostController,
) {

    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(
        title = state.title.asString(),
        navController = navController,
        showBottomBar = false,
        verticalArrangement = Arrangement.Center,
        isRefreshing = state.isRefreshing,
        onRefresh = { viewModel.onAction(LoginAction.Refresh) },
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        LoginScreen(state, viewModel::onAction)
    }
}