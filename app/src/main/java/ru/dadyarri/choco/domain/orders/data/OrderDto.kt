package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.LocalDateTimeSerializer
import ru.dadyarri.choco.serialization.UuidSerializer
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class OrderDto(
    @Serializable(UuidSerializer::class)
    val id: UUID,
    val products: List<OrderedProductDto>,
    @Serializable(LocalDateTimeSerializer::class)
    val orderedAt: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val toBeDeliveredAt: LocalDateTime,
    val orderStatus: OrderStatus,
    val paymentStatus: PaymentStatus,
    val totalAmount: Double,
)