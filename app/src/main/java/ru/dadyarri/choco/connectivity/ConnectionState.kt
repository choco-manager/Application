package ru.dadyarri.choco.connectivity

sealed class ConnectionState {
    object Available: ConnectionState()
    object Unavailable: ConnectionState()
}
