package ru.dadyarri.choco.database

import android.content.Context
import ru.dadyarri.choco.repositories.BooleanConfigurationRepository

interface IAppContainer {
    val booleanConfigurationRepository: BooleanConfigurationRepository
}

class AppContainer(private val context: Context) : IAppContainer {
    override val booleanConfigurationRepository: BooleanConfigurationRepository by lazy {
        BooleanConfigurationRepository(AppDatabase.getDatabase(context).booleanConfigs())
    }
}