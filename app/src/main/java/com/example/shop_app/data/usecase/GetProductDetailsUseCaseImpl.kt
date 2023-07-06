package com.example.shop_app.data.usecase

import com.example.shop_app.components.Response
import com.example.shop_app.data.repository.ProductRepository
import com.example.shop_app.domain.model.Product
import com.example.shop_app.domain.usecase.GetProductDetailsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetProductDetailsUseCase {

    override suspend fun getProductDetails(productId: String): Flow<Response<Product>> {
        return repository.executeProductDetails(productId)
    }
}
