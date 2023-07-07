package ru.dadyarri.choco.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.dadyarri.choco.data.daos.BooleanConfigurationDao
import ru.dadyarri.choco.data.entities.BooleanConfiguration

@Database(entities = [BooleanConfiguration::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun booleanConfigs(): BooleanConfigurationDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app")
                    .build()
                    .also { instance = it }
            }
        }
    }
}