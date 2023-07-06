package com.example.shop_app.domain.usecase

import com.example.shop_app.components.Response
import com.example.shop_app.domain.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {

    suspend fun getProducts(request: String): Flow<Response<ProductResponse>>
}
