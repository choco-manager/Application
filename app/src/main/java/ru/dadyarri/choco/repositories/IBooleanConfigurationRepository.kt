package ru.dadyarri.choco.repositories

import kotlinx.coroutines.flow.Flow
import ru.dadyarri.choco.entities.BooleanConfiguration
import ru.dadyarri.choco.enumerations.ConfigurationKeys

interface IBooleanConfigurationRepository {
    suspend fun insert(entity: BooleanConfiguration)
    suspend fun update(entity: BooleanConfiguration)
    suspend fun getValueOfConfiguration(optionId: ConfigurationKeys): Flow<Boolean>
}