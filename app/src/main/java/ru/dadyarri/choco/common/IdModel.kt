package ru.dadyarri.choco.common

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.networking.UuidSerializer
import java.util.UUID

@Serializable
data class IdModel(
    @Serializable(UuidSerializer::class)
    val id: UUID,
)
