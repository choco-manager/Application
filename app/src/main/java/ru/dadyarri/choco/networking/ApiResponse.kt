package ru.dadyarri.choco.networking

import kotlinx.serialization.Serializable
import ru.dadyarri.choco.common.PagedInfo

@Serializable
data class ApiResponse<T>(
    val value: T? = null,
    val status: Int = 0,
    val isSuccess: Boolean = false,
    val successMessage: String = "",
    val correlationId: String = "",
    val errors: List<String> = emptyList(),
    val validationErrors: List<ValidationError> = emptyList(),
    val pagedInfo: PagedInfo? = null,
)
