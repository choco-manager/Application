package ru.dadyarri.choco.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object HttpClientFactory {
    private const val BASE_URL = "choco.dadyarri.ru/api"
    val httpClient: HttpClient by lazy {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json()
            }

            install(DefaultRequest) {
                url(scheme = "https", host = BASE_URL)
            }
        }
    }
}