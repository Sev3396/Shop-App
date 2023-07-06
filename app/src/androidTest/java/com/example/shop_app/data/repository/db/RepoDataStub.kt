package com.example.shop_app.data.repository.db

import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.domain.model.Product

fun getRecentlyViewedProductStub(): RecentlyViewProducts {
    return RecentlyViewProducts(
        id = 1,
        product = getProductStub()
    )
}

fun getProductStub(): Product {
    return Product(
        brand = "test",
        category = "test",
        description = "test",
        discountPercentage = 0.0,
        id = 1,
        rating = 1.0,
        price = 10,
        stock = 10,
        thumbnail = "test",
        title = "test"
    )
}
