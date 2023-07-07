package ru.dadyarri.choco.components.warehouse

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.dadyarri.choco.data.entities.Product

@Composable
fun ProductCard(product: Product, isWholesalePricesEnabled: Boolean) {

    val unit = if (product.isByWeight) "кг" else "шт."

    Column {
        ListItem(
            headlineContent = { Text(text = product.title) },
            supportingContent = {
                if (isWholesalePricesEnabled) {
                    Text(text = "${product.wholesalePrice}₽ / ${product.retailPrice}₽")
                } else {
                    Text(text = "${product.retailPrice}₽")
                }
            },
            trailingContent = {
                Text(
                    text = if (product.leftover * 10 % 10 == 0.0) "${product.leftover.toInt()} $unit" else "${product.leftover} $unit",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        )
    }
}