package com.example.shop_app.data.usecase

import com.example.shop_app.data.repository.RecentlyViewedProductsRepository
import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.domain.model.Product
import com.example.shop_app.domain.usecase.GetRecentlyViewedProductsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetRecentlyViewProductsUseCaseImpl @Inject constructor(
    private val recentlyViewedProductsRepository: RecentlyViewedProductsRepository
) : GetRecentlyViewedProductsUseCase {

    override suspend fun getRecentlyViewedProducts():
        Flow<List<RecentlyViewProducts>> {
        return recentlyViewedProductsRepository.execute()
    }

    override suspend fun saveViewedProduct(product: Product) {
        recentlyViewedProductsRepository.saveViewedProduct(product)
    }

    override suspend fun clearViewedProducts() {
        recentlyViewedProductsRepository.clearViewedProducts()
    }
}
