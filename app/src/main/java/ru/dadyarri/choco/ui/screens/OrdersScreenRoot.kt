package ru.dadyarri.choco.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.viewmodels.OrdersViewModel

@Composable
fun OrdersScreenRoot(
    navController: NavHostController,
) {
    val viewModel: OrdersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(title = state.title.asString(), navController = navController) {
        OrdersScreen(state = state, onAction = viewModel::onAction)
    }
}