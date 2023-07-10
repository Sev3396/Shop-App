package com.example.shop_app.data.repository.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.shop_app.domain.model.Product
import com.google.gson.Gson

// Used to store & retrieve json object in Room Table
@ProvidedTypeConverter
class RecentlyViewedTypeConverter {

    @TypeConverter
    fun productToJson(product: String): Product {
        return Gson()
            .fromJson(product, Product::class.java)
    }

    @TypeConverter
    fun productToString(product: Product): String {
        return Gson()
            .toJson(product)
    }
}
