package ru.dadyarri.choco.ui.components.common

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.dadyarri.choco.navigation.screen.Screen
import ru.dadyarri.choco.navigation.screen.ScreenPosition
import ru.dadyarri.choco.util.navigate

@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    NavigationBar {
        enumValues<Screen>().filter { screen -> screen.icon != null && screen.position == ScreenPosition.Bottom }
            .forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.name } == true

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { navigate(navController, screen.route, saveEntry = false) },
                    icon = {
                        if (screen.icon != null) {
                            Icon(
                                screen.icon,
                                null,
                                tint = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
            }
    }
}