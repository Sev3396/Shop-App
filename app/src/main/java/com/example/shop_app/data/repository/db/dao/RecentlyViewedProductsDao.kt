package com.example.shop_app.data.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyViewedProductsDao {

    @Upsert
    suspend fun insertProduct(product: RecentlyViewProducts)

    @Query(
        "SELECT * FROM RecentlyViewProducts ORDER BY viewTimestamp DESC"
    )
    fun getRecentlyViewedProducts(): Flow<List<RecentlyViewProducts>>

    @Query(
        "DELETE FROM RecentlyViewProducts"
    )
    suspend fun clearViewedProducts()
}
