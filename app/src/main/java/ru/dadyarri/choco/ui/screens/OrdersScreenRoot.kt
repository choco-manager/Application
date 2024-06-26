package ru.dadyarri.choco.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.ui.actions.OrdersAction
import ru.dadyarri.choco.viewmodels.OrdersViewModel

@Composable
fun OrdersScreenRoot(
    navController: NavHostController,
) {
    val viewModel: OrdersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(
        title = state.title.asString(),
        navController = navController,
        isRefreshing = state.data is Resource.Loading,
        onRefresh = { viewModel.onAction(OrdersAction.Refresh) },
        fabIcon = Icons.Outlined.Add,
        fabAction = { viewModel.onAction(OrdersAction.Create) }
    ) {
        OrdersScreen(state = state, onAction = viewModel::onAction)
    }
}