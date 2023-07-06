package ru.dadyarri.choco.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dadyarri.choco.pages.HomePage
import ru.dadyarri.choco.pages.OrdersPage
import ru.dadyarri.choco.pages.ProfilePage
import ru.dadyarri.choco.pages.ShipmentsPage

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "home",
        androidx.compose.ui.Modifier.padding(innerPadding)
    ) {
        composable("home") { HomePage() }
        composable("orders") { OrdersPage() }
        composable("shipments") { ShipmentsPage() }
        composable("profile") { ProfilePage() }
    }
}