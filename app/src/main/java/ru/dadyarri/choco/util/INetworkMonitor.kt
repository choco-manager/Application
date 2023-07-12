package ru.dadyarri.choco.util

import kotlinx.coroutines.flow.Flow

interface INetworkMonitor {
    val isOnline: Flow<Boolean>
}