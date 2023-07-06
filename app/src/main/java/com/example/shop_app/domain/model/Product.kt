package com.example.shop_app.domain.model

import com.example.shop_app.components.Utils

data class Product(
    val brand: String = Utils.EMPTY_STRING,
    val category: String = Utils.EMPTY_STRING,
    val description: String = Utils.EMPTY_STRING,
    val discountPercentage: Double = Double.MIN_VALUE,
    val id: Int = Int.MIN_VALUE,
    val price: Int = Int.MIN_VALUE,
    val rating: Double = Double.MIN_VALUE,
    val stock: Int = Int.MIN_VALUE,
    val thumbnail: String = Utils.EMPTY_STRING,
    val title: String = Utils.EMPTY_STRING
)
