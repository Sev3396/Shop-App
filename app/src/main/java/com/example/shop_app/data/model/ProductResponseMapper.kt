package com.example.shop_app.data.model

import com.example.shop_app.domain.model.ProductResponse

fun ProductResponse.toDomain(): ProductResponse {
    return ProductResponse(
        limit = limit,
        products = products.map { it.toDomain() },
        skip = skip,
        total = total
    )
}
