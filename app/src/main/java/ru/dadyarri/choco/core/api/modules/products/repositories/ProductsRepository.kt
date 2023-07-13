package ru.dadyarri.choco.core.api.modules.products.repositories

import ru.dadyarri.choco.core.api.modules.products.fake.FakeProductDtos
import javax.inject.Inject

class ProductsRepository @Inject constructor() {
    fun getProducts() = FakeProductDtos.data
}