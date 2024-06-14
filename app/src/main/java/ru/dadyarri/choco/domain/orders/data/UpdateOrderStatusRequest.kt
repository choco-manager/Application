package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable

@Serializable
data class UpdateOrderStatusRequest(
    val status: OrderStatus,
)