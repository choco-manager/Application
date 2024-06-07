package ru.dadyarri.choco.domain.auth.services

import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.auth.data.LoginRequest
import ru.dadyarri.choco.domain.auth.data.LoginResponse
import ru.dadyarri.choco.domain.auth.data.RestorationTokenResponse
import ru.dadyarri.choco.domain.auth.data.RestorePasswordRequest
import ru.dadyarri.choco.domain.auth.data.UsernameRequest
import ru.dadyarri.choco.domain.auth.data.WhoamiResponse
import ru.dadyarri.choco.networking.AuthManager
import ru.dadyarri.choco.networking.HttpClientFactory
import ru.dadyarri.choco.networking.safeRequest
import javax.inject.Inject

class AuthService @Inject constructor(private val authManager: AuthManager) {

    suspend fun login(body: LoginRequest): Resource<LoginResponse> {
        val client = HttpClientFactory.httpClient
        return client.safeRequest<LoginResponse> {
            method = HttpMethod.Post
            url("v3/auth/login")
            setBody(body)
        }
    }

    suspend fun logout(): Resource<EmptyContent> {
        val client = HttpClientFactory.httpClient
        return client.safeRequest<EmptyContent> {
            method = HttpMethod.Post
            url("v3/auth/logout")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun whoami(): Resource<WhoamiResponse> {
        val httpClient = HttpClientFactory.httpClient
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url("v3/auth/whoami")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun requestRestoration(body: UsernameRequest): Resource<RestorationTokenResponse> {
        val httpClient = HttpClientFactory.httpClient
        return httpClient.safeRequest {
            method = HttpMethod.Post
            url("v3/auth/request-restoration")
            setBody(body)
        }
    }

    suspend fun restorePassword(body: RestorePasswordRequest): Resource<LoginResponse> {
        val client = HttpClientFactory.httpClient
        return client.safeRequest<LoginResponse> {
            method = HttpMethod.Post
            url("v3/auth/restore")
            setBody(body)
        }
    }
}