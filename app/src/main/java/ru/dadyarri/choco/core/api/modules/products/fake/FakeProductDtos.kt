package ru.dadyarri.choco.core.api.modules.products.fake

import ru.dadyarri.choco.core.api.modules.products.contracts.ProductDto
import java.util.UUID

sealed class FakeProductDtos {
    companion object {
        val data = listOf(
            ProductDto(
                UUID.randomUUID(),
                "Молочный",
                "Молочные",
                isByWeight = false,
                isDeleted = false,
                vkMarketId = 0,
                retailPrice = 234.0f,
                wholesalePrice = 123.0f,
                leftover = 10.0f
            ),
            ProductDto(
                UUID.randomUUID(),
                "Мармелад",
                "Мармелады",
                isByWeight = true,
                isDeleted = false,
                vkMarketId = 0,
                retailPrice = 234.0f,
                wholesalePrice = 123.0f,
                leftover = 10.0f
            ),
            ProductDto(
                UUID.randomUUID(),
                "Горький 72%",
                "Горькие",
                isByWeight = false,
                isDeleted = false,
                vkMarketId = 0,
                retailPrice = 234.0f,
                wholesalePrice = 123.0f,
                leftover = 10.0f
            ),
            ProductDto(
                UUID.randomUUID(),
                "Молочный цел/фунд",
                "Молочные",
                isByWeight = false,
                isDeleted = false,
                vkMarketId = 0,
                retailPrice = 234.0f,
                wholesalePrice = 123.0f,
                leftover = 10.0f
            ),
            ProductDto(
                UUID.randomUUID(),
                "Молочный цел/минд",
                "Молочные",
                isByWeight = false,
                isDeleted = false,
                vkMarketId = 0,
                retailPrice = 234.0f,
                wholesalePrice = 123.0f,
                leftover = 10.0f
            ),
        )
    }
}
