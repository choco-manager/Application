package ru.dadyarri.choco.navigation.routes

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Login: Route()
    @Serializable
    data object RequestPasswordRestoration : Route()
    @Serializable
    data object RestorePassword : Route()

    @Serializable
    data object Orders: Route()
    @Serializable
    data object Procurements: Route()
    @Serializable
    data object Menu: Route()
}