package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePaymentStatusRequest(
    val status: PaymentStatus,
)