package ru.dadyarri.choco.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.Scale
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dadyarri.choco.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarehousePage(viewModel: MainActivityViewModel = hiltViewModel()) {
    val products = viewModel.getProductsData()
    val prefs = viewModel.getPreferences().collectAsState().value
    Column {
        products.forEach { product ->
            val unit = if (product.isByWeight) "кг" else "шт."
            ListItem(
                overlineText = { Text(text = product.categoryName) },
                leadingContent = {
                    if (product.isByWeight) {
                        Icon(imageVector = Icons.Rounded.Scale, contentDescription = null)
                    } else {
                        Icon(Icons.Rounded.Inventory2, null)
                    }
                },
                headlineText = { Text(text = product.name) },
                supportingText = {
                    if (prefs.showWholesalePrices) {
                        Text(text = "${product.retailPrice}₽ / ${product.wholesalePrice}₽")
                    } else {
                        Text(text = "${product.retailPrice}₽")
                    }
                },
                trailingContent = {
                    Text(
                        text = if (product.leftover * 10 % 10 == 0.0f) "${product.leftover.toInt()} $unit" else "${product.leftover} $unit",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            )

        }
    }
}
