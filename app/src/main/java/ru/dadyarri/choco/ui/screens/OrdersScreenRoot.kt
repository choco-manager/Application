package ru.dadyarri.choco.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.ui.actions.OrdersAction
import ru.dadyarri.choco.viewmodels.OrdersViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
        fab = {
            IconButton(onClick = { viewModel.onAction(OrdersAction.Create) }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }
    ) {
        PullToRefreshBox(
            isRefreshing = state.data is Resource.Loading,
            onRefresh = { viewModel.onAction(OrdersAction.Refresh) }
        ) {
            OrdersScreen(state = state, onAction = viewModel::onAction)
        }
    }
}