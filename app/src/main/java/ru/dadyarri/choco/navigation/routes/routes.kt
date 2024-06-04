package ru.dadyarri.choco.navigation.routes

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Login: Route()
    @Serializable
    data object RestorePassword: Route()
}