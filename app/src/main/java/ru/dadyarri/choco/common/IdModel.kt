package ru.dadyarri.choco.common

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.UuidSerializer
import java.util.UUID

@Serializable
data class IdModel(
    @Serializable(UuidSerializer::class)
    val id: UUID,
)
