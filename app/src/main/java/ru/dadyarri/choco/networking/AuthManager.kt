package ru.dadyarri.choco.networking

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.domain.auth.data.LoginResponse
import ru.dadyarri.choco.domain.auth.data.RefreshRequest
import ru.dadyarri.choco.storage.DataStoreManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStoreManager: DataStoreManager,
    private val httpClient: HttpClient,
) {

    private fun getPrefixedToken(accessToken: String): String {
        return UiText.StringResource(R.string.token_prefix, accessToken)
            .asString(context)
    }

    private suspend fun getAuthorizationHeader(): String {
        return getPrefixedToken(dataStoreManager.getAccessToken())
    }

    private suspend fun accessTokenIsValid(): Boolean {
        val response = httpClient.safeRequest<String> {
            method = HttpMethod.Get
            url("v3/ping")
            header(HttpHeaders.Authorization, getAuthorizationHeader())
        }

        return response is Resource.Success
    }

    private suspend fun refreshTokens(): String {
        val response = httpClient.safeRequest<LoginResponse> {
            method = HttpMethod.Get
            url("v3/auth/refresh")
            setBody(RefreshRequest(dataStoreManager.getRefreshToken()))
        }


        return if (response is Resource.Success) {
            dataStoreManager.updateRefreshToken(refreshToken = response.data!!.refreshToken)
            dataStoreManager.updateAccessToken(accessToken = response.data.accessToken)
            response.data.accessToken
        } else {
            ""
        }
    }

    suspend fun getCurrentAuthorizationHeader(): String {
        return if (accessTokenIsValid()) {
            getAuthorizationHeader()
        } else {
            getPrefixedToken(refreshTokens())
        }
    }

}