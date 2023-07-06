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
        """
        select *
        from RecentlyViewProducts
        order by viewTimestamp desc
        """
    )
    fun getRecentlyViewedProducts(): Flow<List<RecentlyViewProducts>>

    @Query(
        """
            delete
            from RecentlyViewProducts
        """
    )
    suspend fun clearViewedProducts()
}
