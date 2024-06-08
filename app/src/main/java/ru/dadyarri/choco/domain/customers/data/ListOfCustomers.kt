package ru.dadyarri.choco.domain.customers.data

import kotlinx.serialization.Serializable

@Serializable
data class ListOfCustomers(
    val customers: List<CustomerDto>,
)