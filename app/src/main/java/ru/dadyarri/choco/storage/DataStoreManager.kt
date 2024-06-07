package ru.dadyarri.choco.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun updateAccessToken(accessToken: String) {
        context.dataStore.updateData {
            it.copy(
                accessToken = accessToken
            )
        }
    }

    suspend fun updateRefreshToken(refreshToken: String) {
        context.dataStore.updateData {
            it.copy(
                refreshToken = refreshToken
            )
        }
    }
}