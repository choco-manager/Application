package ru.dadyarri.choco.components.warehouse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dadyarri.choco.entities.Product

@Composable
fun ProductCard(product: Product, isWholesalePricesEnabled: Boolean) {

    val unit = if (product.isByWeight) "кг" else "шт."

    Column {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = product.title, style = MaterialTheme.typography.headlineSmall)

                if (isWholesalePricesEnabled) {
                    Text(text = "${product.wholesalePrice}₽ / ${product.retailPrice}₽")
                } else {
                    Text(text = "${product.retailPrice}₽")
                }
            }
            Text(
                text = if (product.leftover * 10 % 10 == 0.0) "${product.leftover.toInt()} $unit" else "${product.leftover} $unit",
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}