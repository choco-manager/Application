package ru.dadyarri.choco.components.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warehouse
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dadyarri.choco.R

sealed class Screen(val route: String, @StringRes val labelId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.page_home, Icons.Outlined.Home)
    object Orders : Screen("orders", R.string.page_orders, Icons.Outlined.CloudUpload)
    object Shipments : Screen("shipments", R.string.page_shipments, Icons.Outlined.CloudDownload)
    object Warehouse: Screen("warehouse", R.string.page_warehouse, Icons.Outlined.Warehouse)
    object Profile : Screen("profile", R.string.page_profile, Icons.Outlined.Person)
}