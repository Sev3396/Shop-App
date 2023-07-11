package com.example.shop_app.ui.view.global_components

import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts

// Stores the state of RecentlyViewed Items
data class RecentlyViewedProductsState(
    val recentlyViewedProducts: List<RecentlyViewProducts> = listOf()
)
