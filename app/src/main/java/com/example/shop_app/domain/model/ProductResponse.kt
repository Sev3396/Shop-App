package com.example.shop_app.domain.model

data class ProductResponse(
    val limit: Int = Int.MIN_VALUE,
    val products: List<Product> = emptyList(),
    val skip: Int = Int.MIN_VALUE,
    val total: Int = Int.MIN_VALUE
)
