package ru.dadyarri.choco.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.ui.actions.OrderAction
import ru.dadyarri.choco.viewmodels.OrderViewModel
import java.util.UUID

@Composable
fun OrderScreenRoot(
    navController: NavHostController,
    args: Route.Order,
) {
    val viewModel = hiltViewModel<OrderViewModel, OrderViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(UUID.fromString(args.id))
        }
    )
    val state by viewModel.state.collectAsState()

    BaseScreen(
        title = state.title.asString(),
        navController = navController,
        isRefreshing = state.data is Resource.Loading,
        onRefresh = { viewModel.onAction(OrderAction.Refresh) },
        fabIcon = Icons.Outlined.Edit,
        fabAction = { viewModel.onAction(OrderAction.Edit) }
    ) {
        OrderScreen(state = state, onAction = viewModel::onAction)
    }
}