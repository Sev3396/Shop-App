package com.example.shop_app.ui.view.screen.product_search

import com.example.shop_app.domain.model.ProductResponse

data class ProductListState(
    val isLoading: Boolean = false,
    val productList: ProductResponse = ProductResponse(),
    val error: String = ""
)
