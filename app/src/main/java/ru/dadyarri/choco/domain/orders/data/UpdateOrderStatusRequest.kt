package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.UuidSerializer
import java.util.UUID

@Serializable
data class UpdateOrderStatusRequest(
    @Serializable(UuidSerializer::class)
    val id: UUID,
    val status: OrderStatus,
)