package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.LocalDateTimeSerializer
import ru.dadyarri.choco.serialization.UuidSerializer
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class CreateOrderRequest(
    @Serializable(LocalDateTimeSerializer::class)
    val toBeDeliveredAt: LocalDateTime,
    @Serializable(UuidSerializer::class)
    val customerId: UUID,
    val products: List<CreateOrderProductRequest>,
)