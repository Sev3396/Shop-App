package com.example.shop_app.ui.view.screen.product_details

import com.example.shop_app.domain.model.Product

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val productDetails: Product = Product(),
    val error: String = ""
)
