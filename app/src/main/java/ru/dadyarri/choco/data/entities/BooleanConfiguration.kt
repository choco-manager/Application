package ru.dadyarri.choco.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boolConfigs")
data class BooleanConfiguration(
    @PrimaryKey
    val id: Int,
    val value: Boolean
)
