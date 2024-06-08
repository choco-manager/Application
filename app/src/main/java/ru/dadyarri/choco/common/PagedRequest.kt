package ru.dadyarri.choco.common

data class PagedRequest(
    val pageSize: Int = 10,
    val page: Int = 1,
)