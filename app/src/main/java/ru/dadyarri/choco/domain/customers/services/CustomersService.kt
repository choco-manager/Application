package ru.dadyarri.choco.domain.customers.services

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import ru.dadyarri.choco.common.IdModel
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.customers.data.CreateCustomerRequest
import ru.dadyarri.choco.domain.customers.data.ListOfCustomers
import ru.dadyarri.choco.networking.AuthManager
import ru.dadyarri.choco.networking.safeRequest
import javax.inject.Inject

class CustomersService @Inject constructor(
    private val authManager: AuthManager,
    private val httpClient: HttpClient,
) {

    suspend fun getAllCCustomers(): Resource<ListOfCustomers> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url("v3/customers")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun createCustomer(body: CreateCustomerRequest): Resource<IdModel> {
        return httpClient.safeRequest {
            method = HttpMethod.Post
            url("v3/customers")
            setBody(body)
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

}