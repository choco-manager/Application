package ru.dadyarri.choco.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Warehouse
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dadyarri.choco.R

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.app_name, Icons.Outlined.Home)
    object Orders : Screen("orders", R.string.page_orders, Icons.Outlined.CloudUpload)
    object Shipments : Screen("orders", R.string.page_shipments, Icons.Outlined.CloudDownload)
    object Warehouse : Screen("orders", R.string.page_warehouse, Icons.Outlined.Warehouse)
    object Settings : Screen("orders", R.string.page_settings, Icons.Outlined.Settings)

}