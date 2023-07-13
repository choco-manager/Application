package ru.dadyarri.choco.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    private object PreferencesKeys {
        val darkThemeConfig = stringPreferencesKey("darkThemeConfig")
        val serverConfig = stringPreferencesKey("serverConfig")
        val showWholesalePrices = booleanPreferencesKey("showWholesalePrices")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences -> mapUserPreferences(preferences) }

    suspend fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.darkThemeConfig] = darkThemeConfig.name
        }
    }

    suspend fun updateServerConfig(serverConfig: ServerConfig) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.serverConfig] = serverConfig.name
        }
    }

    suspend fun updateShowWholesalePrices(enabled: Boolean) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.showWholesalePrices] = enabled }
    }

    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val darkThemeConfig = DarkThemeConfig.valueOf(
            preferences[PreferencesKeys.darkThemeConfig] ?: DarkThemeConfig.FOLLOW_SYSTEM.name
        )
        val serverConfig = ServerConfig.valueOf(
            preferences[PreferencesKeys.serverConfig] ?: ServerConfig.PRODUCTION.name
        )
        val showWholesalePrices = preferences[PreferencesKeys.showWholesalePrices] ?: true
        return UserPreferences(darkThemeConfig, serverConfig, showWholesalePrices)
    }

}