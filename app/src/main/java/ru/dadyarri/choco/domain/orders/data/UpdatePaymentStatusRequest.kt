package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.UuidSerializer
import java.util.UUID

@Serializable
data class UpdatePaymentStatusRequest(
    @Serializable(UuidSerializer::class)
    val id: UUID,
    val status: PaymentStatus,
)