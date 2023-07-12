package ru.dadyarri.choco.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.dadyarri.choco.util.ConnectivityManagerNetworkMonitor
import ru.dadyarri.choco.util.INetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DependencyBindings {
    @Binds
    fun bindsNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): INetworkMonitor
}