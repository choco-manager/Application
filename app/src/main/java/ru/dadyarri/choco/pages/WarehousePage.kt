package ru.dadyarri.choco.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import ru.dadyarri.choco.components.warehouse.ProductCard
import ru.dadyarri.choco.entities.Product

@Composable
fun WarehousePage(isWholesalePricesEnabled: MutableState<Boolean>) {
    val products = listOf(
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.2,
            isByWeight = true
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
        Product(
            "Молочный",
            wholesalePrice = 200.0,
            retailPrice = 350.0,
            leftover = 10.0,
            isByWeight = false
        ),
    )
    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        items(products) { product ->
            ProductCard(
                product = product,
                isWholesalePricesEnabled = isWholesalePricesEnabled.value
            )
        }
    }
}