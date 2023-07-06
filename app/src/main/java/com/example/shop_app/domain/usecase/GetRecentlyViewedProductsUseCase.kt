package com.example.shop_app.domain.usecase

import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetRecentlyViewedProductsUseCase {

    suspend fun getRecentlyViewedProducts(): Flow<List<RecentlyViewProducts>>

    suspend fun saveViewedProduct(product: Product)

    suspend fun clearViewedProducts()
}
