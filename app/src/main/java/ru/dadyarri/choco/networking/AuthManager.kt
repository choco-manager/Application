package ru.dadyarri.choco.networking

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.storage.DataStoreManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStoreManager: DataStoreManager,
) {
    suspend fun getAuthorizationHeader(): String {
        return UiText.StringResource(R.string.token_prefix, dataStoreManager.getAccessToken())
            .asString(context)
    }

}