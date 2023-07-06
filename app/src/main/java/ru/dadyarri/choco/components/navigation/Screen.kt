package ru.dadyarri.choco.components.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudDownload
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dadyarri.choco.R

sealed class Screen(val route: String, @StringRes val labelId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.page_home, Icons.Rounded.Home)
    object Orders : Screen("orders", R.string.page_orders, Icons.Rounded.CloudUpload)
    object Shipments : Screen("shipments", R.string.page_shipments, Icons.Rounded.CloudDownload)
    object Profile : Screen("profile", R.string.page_profile, Icons.Rounded.Person)
}