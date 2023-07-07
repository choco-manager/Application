package ru.dadyarri.choco.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.dadyarri.choco.entities.BooleanConfiguration
import ru.dadyarri.choco.enumerations.ConfigurationKeys

@Dao
interface BooleanConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BooleanConfiguration)

    @Update
    suspend fun update(entity: BooleanConfiguration)

    @Query("SELECT value from boolConfigs where id = :optionId")
    fun getValueOfConfiguration(optionId: ConfigurationKeys): Flow<Boolean>
}