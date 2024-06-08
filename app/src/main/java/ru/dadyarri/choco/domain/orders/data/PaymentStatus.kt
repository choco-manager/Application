package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentStatus {
    Pending,
    Paid
}