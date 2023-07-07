package ru.dadyarri.choco.repositories

import kotlinx.coroutines.flow.Flow
import ru.dadyarri.choco.daos.BooleanConfigurationDao
import ru.dadyarri.choco.entities.BooleanConfiguration
import ru.dadyarri.choco.enumerations.ConfigurationKeys

class BooleanConfigurationRepository(private val dao: BooleanConfigurationDao) :
    IBooleanConfigurationRepository {
    override suspend fun insert(entity: BooleanConfiguration) = dao.insert(entity)

    override suspend fun update(entity: BooleanConfiguration) = dao.update(entity)

    override suspend fun getValueOfConfiguration(optionId: ConfigurationKeys): Flow<Boolean> =
        dao.getValueOfConfiguration(optionId)
}