package com.example.shop_app.data.network

import com.example.shop_app.domain.model.Product
import com.example.shop_app.domain.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/products/search")
    suspend fun getProducts(
        @Query(value = "q") productName: String
    ): ProductResponse

    @GET("/products/{productId}")
    suspend fun getProductDetails(
        @Path(value = "productId") productId: String
    ): Product
}
