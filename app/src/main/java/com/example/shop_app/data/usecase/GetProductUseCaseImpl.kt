package com.example.shop_app.data.usecase

import com.example.shop_app.components.Response
import com.example.shop_app.data.repository.ProductRepository
import com.example.shop_app.domain.model.ProductResponse
import com.example.shop_app.domain.usecase.GetProductUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductUseCase {

    override suspend fun getProducts(request: String): Flow<Response<ProductResponse>> {
        return productRepository.execute(request)
    }
}
