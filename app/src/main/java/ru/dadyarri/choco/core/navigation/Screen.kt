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

enum class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    Home("home", R.string.app_name, Icons.Outlined.Home),
    Orders("orders", R.string.page_orders, Icons.Outlined.CloudUpload),
    Shipments("orders", R.string.page_shipments, Icons.Outlined.CloudDownload),
    Warehouse("orders", R.string.page_warehouse, Icons.Outlined.Warehouse),
    Settings("orders", R.string.page_settings, Icons.Outlined.Settings),

}