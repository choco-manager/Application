package ru.dadyarri.choco.navigation.routes

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.serialization.UuidSerializer
import java.util.UUID

sealed class Route {
    @Serializable
    data object Login : Route()

    @Serializable
    data object RequestPasswordRestoration : Route()

    @Serializable
    data object RestorePassword : Route()

    @Serializable
    data object Orders : Route()

    @Serializable
    data object CreateOrder : Route()

    @Serializable
    data class Order(@Serializable(UuidSerializer::class) val id: UUID) : Route()

    @Serializable
    data object Procurements : Route()

    @Serializable
    data object Menu : Route()
}