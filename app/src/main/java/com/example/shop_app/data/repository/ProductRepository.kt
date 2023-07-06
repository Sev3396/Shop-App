package com.example.shop_app.data.repository

import com.example.shop_app.components.Response
import com.example.shop_app.components.Utils
import com.example.shop_app.data.model.toDomain
import com.example.shop_app.data.network.ProductApi
import com.example.shop_app.domain.model.Product
import com.example.shop_app.domain.model.ProductResponse
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ProductRepository @Inject constructor(
    private val api: ProductApi
) {

    fun execute(productName: String): Flow<Response<ProductResponse>> = flow {
        try {
            emit(Response.Loading())
            val response = api.getProducts(productName).toDomain()
            if (response.total == 0) {
                emit(Response.Error("No products found for the search!!!"))
            } else {
                emit(Response.Success(response))
            }
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Response.Error(Utils.errorMessage))
        }
    }

    fun executeProductDetails(productId: String): Flow<Response<Product>> = flow {
        try {
            emit(Response.Loading())
            val response = api.getProductDetails(productId).toDomain()
            if (response.id.toString().isEmpty()) {
                emit(Response.Error("No products found for the search!!!"))
            } else {
                emit(Response.Success(response))
            }
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Response.Error(Utils.errorMessage))
        }
    }
}
