package com.example.shop_app.domain.usecase

import com.example.shop_app.components.Response
import com.example.shop_app.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductDetailsUseCase {

    suspend fun getProductDetails(productId: String): Flow<Response<Product>>
}
