package ru.dadyarri.choco.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.dadyarri.choco.core.api.modules.products.repositories.ProductsRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesInjection {
    @Binds
    fun bindsProductsRepository(productsRepository: ProductsRepository): ProductsRepository
}