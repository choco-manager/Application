package ru.dadyarri.choco.domain.customers.data

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.networking.UuidSerializer
import java.util.UUID

@Serializable
data class AddressDto(
    @Serializable(UuidSerializer::class)
    val id: UUID,
    val address: String,
)
