package com.example.shop_app.data.model

import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.domain.model.Product

fun Product.toDomain(): Product {
    return Product(
        brand = brand,
        category = category,
        description = description,
        discountPercentage = discountPercentage,
        id = id,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )
}

fun RecentlyViewProducts.toViewedDomain(): RecentlyViewProducts {
    return RecentlyViewProducts(
        id = id,
        product = product
    )
}
