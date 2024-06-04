package ru.dadyarri.choco.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dadyarri.choco.navigation.routes.Route

enum class Screen(val icon: ImageVector?, val config: ScreenConfig, val route: Route) {
    Orders(
        Icons.Outlined.CloudDownload,
        ScreenConfig(showBottomBar = true, ScreenPosition.Bottom),
        Route.Orders
    ),
    Procurements(
        Icons.Outlined.CloudUpload,
        ScreenConfig(showBottomBar = true, ScreenPosition.Bottom),
        Route.Procurements
    ),
    Menu(
        Icons.Outlined.Menu,
        ScreenConfig(showBottomBar = true, ScreenPosition.Bottom),
        Route.Menu
    )
}