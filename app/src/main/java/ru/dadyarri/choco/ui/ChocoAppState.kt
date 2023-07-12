package ru.dadyarri.choco.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.dadyarri.choco.core.navigation.Screen
import ru.dadyarri.choco.util.INetworkMonitor

@Composable
fun rememberAppState(
    networkMonitor: INetworkMonitor,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): ChocoAppState {
    return remember(networkMonitor, navController, coroutineScope) {
        ChocoAppState(networkMonitor, navController, coroutineScope)
    }
}

class ChocoAppState(
    val networkMonitor: INetworkMonitor,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    fun navigateToTopLevelDestination(screen: Screen) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (screen) {
            Screen.Home -> navController.navigateToHome(topLevelNavOptions)
            Screen.Orders -> navController.navigateToOrders(topLevelNavOptions)
            Screen.Shipments -> navController.navigateToShipments(topLevelNavOptions)
            Screen.Warehouse -> navController.navigateToWarehouse(topLevelNavOptions)
            Screen.Settings -> navController.navigateToSettings(topLevelNavOptions)
        }
    }

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: Screen?
        @Composable get() = when (currentDestination?.route) {
            "home" -> Screen.Home
            "orders" -> Screen.Orders
            "shipments" -> Screen.Shipments
            "warehouse" -> Screen.Warehouse
            "settings" -> Screen.Settings
            else -> null
        }

    val topLevelDestinations = Screen.values().toList()

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}

private fun NavHostController.navigateToSettings(topLevelNavOptions: NavOptions) {
    this.navigate("settings", topLevelNavOptions)
}

private fun NavHostController.navigateToWarehouse(topLevelNavOptions: NavOptions) {
    this.navigate("warehouse", topLevelNavOptions)
}

private fun NavHostController.navigateToShipments(topLevelNavOptions: NavOptions) {
    this.navigate("shipments", topLevelNavOptions)
}

private fun NavHostController.navigateToOrders(topLevelNavOptions: NavOptions) {
    this.navigate("orders", topLevelNavOptions)
}

private fun NavHostController.navigateToHome(topLevelNavOptions: NavOptions) {
    this.navigate("home", topLevelNavOptions)
}
