package ru.dadyarri.choco.domain.orders.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.UuidSerializer
import java.util.UUID

@Serializable
data class CreateOrderProductRequest(
    @Serializable(UuidSerializer::class)
    val product: UUID,
    val amount: Double,
)

