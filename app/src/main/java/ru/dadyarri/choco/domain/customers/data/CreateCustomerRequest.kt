package ru.dadyarri.choco.domain.customers.data

import kotlinx.serialization.Serializable

@Serializable
data class CreateCustomerRequest(
    val name: String,
    val phoneNumber: String?,
    val vkId: Long?,
    val deliveryAddresses: List<String>,
)
