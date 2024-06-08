package ru.dadyarri.choco.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.common.UiText

suspend inline fun <reified T> HttpClient.safeRequest(block: HttpRequestBuilder.() -> Unit): Resource<T> {
    return try {
        val response = request {
            block()

            if (this.method == HttpMethod.Post) {
                contentType(ContentType.Application.Json)
            }

            header(HttpHeaders.Accept, ContentType.Application.Json)
        }

        when (response.status) {
            HttpStatusCode.OK -> Resource.Success(
                response.parseBody<T>().value!!
            )

            HttpStatusCode.Created -> Resource.Success(
                response.parseBody<T>().value!!
            )

            HttpStatusCode.NoContent -> Resource.SuccessNoData()

            HttpStatusCode.Unauthorized -> Resource.Error(
                UiText.StringResource(R.string.authorization_error)
            )

            HttpStatusCode.NotFound -> Resource.Error(
                UiText.StringResource(R.string.not_found, response.parseBody<T>().errors[0])
            )

            else -> Resource.Error(
                UiText.StringResource(R.string.unknown_error),
            )
        }

    } catch (e: ClientRequestException) {
        Resource.Error(UiText.StringResource(R.string.client_error))
    } catch (e: ServerResponseException) {
        Resource.Error(UiText.StringResource(R.string.server_error))
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.io_error))
    } catch (e: SerializationException) {
        Resource.Error(UiText.StringResource(R.string.serialization_error))
    }
}

suspend inline fun <reified T> HttpResponse.parseBody(): ApiResponse<T> {
    val json = Json { ignoreUnknownKeys = true }
    return try {
        val stringBody = bodyAsText()
        json.decodeFromString<ApiResponse<T>>(stringBody)
    } catch (e: SerializationException) {
        e.printStackTrace()
        ApiResponse()
    }
}