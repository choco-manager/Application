package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable

@Serializable
data class OrderedProductDto(
    val productName: String,
    val amount: Double,
    val price: Double,
)