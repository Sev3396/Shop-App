package com.example.shop_app.data.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shop_app.data.repository.db.RecentlyViewedTypeConverter
import com.example.shop_app.domain.model.Product

@Entity()
@TypeConverters(value = [RecentlyViewedTypeConverter::class])
data class RecentlyViewProducts(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val product: Product,
    val viewTimestamp: Long = System.currentTimeMillis()
)
