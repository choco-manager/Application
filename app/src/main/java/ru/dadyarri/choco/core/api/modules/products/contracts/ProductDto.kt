package ru.dadyarri.choco.core.api.modules.products.contracts

import java.util.UUID

data class ProductDto(
    val id: UUID,
    val name: String,
    val categoryName: String,
    val isByWeight: Boolean,
    val isDeleted: Boolean,
    val vkMarketId: Int,
    val retailPrice: Float,
    val wholesalePrice: Float,
    val leftover: Float
)
