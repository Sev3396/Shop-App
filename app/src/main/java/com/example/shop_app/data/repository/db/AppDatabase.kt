package com.example.shop_app.data.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shop_app.data.repository.db.dao.RecentlyViewedProductsDao
import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts

@Database(
    version = 1,
    entities = [RecentlyViewProducts::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recentlyViewedProductsDao(): RecentlyViewedProductsDao
}
