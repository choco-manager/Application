package ru.dadyarri.choco.util

import androidx.navigation.NavHostController
import ru.dadyarri.choco.navigation.routes.Route

fun navigate(navController: NavHostController, route: Route, saveEntry: Boolean = true) {
    navController.navigate(route) {
        if (!saveEntry) {
            popUpTo(navController.currentDestination?.route ?: "") {
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}