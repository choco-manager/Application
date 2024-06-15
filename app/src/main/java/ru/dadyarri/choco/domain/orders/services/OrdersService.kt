package ru.dadyarri.choco.domain.orders.services

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import ru.dadyarri.choco.common.IdModel
import ru.dadyarri.choco.common.PagedRequest
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.orders.data.CreateOrderRequest
import ru.dadyarri.choco.domain.orders.data.ExtendedOrderDto
import ru.dadyarri.choco.domain.orders.data.ListOfOrders
import ru.dadyarri.choco.domain.orders.data.UpdateOrderStatusRequest
import ru.dadyarri.choco.domain.orders.data.UpdatePaymentStatusRequest
import ru.dadyarri.choco.networking.AuthManager
import ru.dadyarri.choco.networking.safeRequest
import java.util.UUID
import javax.inject.Inject

class OrdersService @Inject constructor(
    private val authManager: AuthManager,
    private val httpClient: HttpClient,
) {

    suspend fun getAllOrders(paged: PagedRequest = PagedRequest()): Resource<ListOfOrders> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url(path = "v3/orders") {
                parameters.append("pageSize", paged.pageSize.toString())
                parameters.append("page", paged.page.toString())
            }
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun getOrder(id: UUID): Resource<ExtendedOrderDto> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url("v3/orders/${id}")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun create(body: CreateOrderRequest): Resource<IdModel> {
        return httpClient.safeRequest {
            method = HttpMethod.Post
            url("v3/orders")
            setBody(body)
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun softDelete(id: UUID): Resource<EmptyContent> {
        return httpClient.safeRequest {
            method = HttpMethod.Delete
            url("v3/orders/${id}")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun forceDelete(id: UUID): Resource<EmptyContent> {
        return httpClient.safeRequest {
            method = HttpMethod.Delete
            url("v3/orders/${id}/force")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun restore(id: UUID): Resource<EmptyContent> {
        return httpClient.safeRequest {
            method = HttpMethod.Put
            url("v3/orders/${id}")
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun updateOrderStatus(id: UUID, body: UpdateOrderStatusRequest): Resource<IdModel> {
        return httpClient.safeRequest {
            method = HttpMethod.Patch
            url("v3/orders/${id}/status")
            setBody(body)
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

    suspend fun updatePaymentStatus(id: UUID, body: UpdatePaymentStatusRequest): Resource<IdModel> {
        return httpClient.safeRequest {
            method = HttpMethod.Patch
            url("v3/orders/${id}/payment")
            setBody(body)
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, authManager.getCurrentAuthorizationHeader())
        }
    }

}