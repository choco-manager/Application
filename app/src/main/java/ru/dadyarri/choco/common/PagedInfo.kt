package ru.dadyarri.choco.common

import kotlinx.serialization.Serializable

@Serializable
data class PagedInfo(
    val pageNumber: Long,
    val pageSize: Long,
    val totalPages: Long,
    val totalRecords: Long,
)
