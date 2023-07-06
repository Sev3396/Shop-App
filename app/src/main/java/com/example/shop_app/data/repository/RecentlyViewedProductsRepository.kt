package com.example.shop_app.data.repository

import com.example.shop_app.data.repository.db.dao.RecentlyViewedProductsDao
import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.domain.model.Product
import javax.inject.Inject
import kotlinx.coroutines.flow.*

class RecentlyViewedProductsRepository @Inject constructor(
    private val recentlyViewedProductsDao: RecentlyViewedProductsDao
) {
    fun execute(): Flow<List<RecentlyViewProducts>> {
        try {
            return recentlyViewedProductsDao.getRecentlyViewedProducts()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return flowOf()
    }

    suspend fun saveViewedProduct(product: Product) {
        val productsEntity = RecentlyViewProducts(
            id = product.id,
            product = product
        )
        recentlyViewedProductsDao.insertProduct(productsEntity)
    }

    suspend fun clearViewedProducts() {
        recentlyViewedProductsDao.clearViewedProducts()
    }
}
