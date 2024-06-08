package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable

@Serializable
enum class OrderStatus {
    Pending,
    Processing,
    Shipped,
    Delivered,
    Cancelled,
    OnHold
}