package ru.dadyarri.choco.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dadyarri.choco.navigation.routes.Route

enum class Screen(val icon: ImageVector?, val position: ScreenPosition, val route: Route) {
    Orders(
        icon = Icons.Outlined.CloudDownload,
        position = ScreenPosition.Bottom,
        route = Route.Orders
    ),
    Procurements(
        icon = Icons.Outlined.CloudUpload,
        position = ScreenPosition.Bottom,
        route = Route.Procurements
    ),
    Menu(
        icon = Icons.Outlined.Menu,
        position = ScreenPosition.Bottom,
        route = Route.Menu
    )
}