package com.example.shop_app.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.components.Response
import com.example.shop_app.domain.model.Product
import com.example.shop_app.domain.model.ProductResponse
import com.example.shop_app.domain.usecase.GetProductUseCase
import com.example.shop_app.ui.view.screen.product_search.ProductListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

fun getProductResponseStub(): ProductResponse {
    return ProductResponse(
        limit = 1,
        products = listOf(getProductStub()),
        skip = 1,
        total = 1
    )
}

fun getProductStub(): Product {
    return Product(
        brand = "test",
        category = "test",
        description = "test",
        discountPercentage = 0.0,
        id = 1,
        rating = 1.0,
        price = 10,
        stock = 10,
        thumbnail = "test",
        title = "test"
    )
}

fun getProductListStateStub(): MutableState<ProductListState> {
    return mutableStateOf(
        ProductListState(
            isLoading = true,
            productList = ProductResponse(),
            error = ""
        )
    )
}

class GetProductUseCaseStub : GetProductUseCase {

    private val flow = MutableSharedFlow<Response<ProductResponse>>()

    suspend fun emit(value: Response<ProductResponse>) = flow.emit(value)

    override suspend fun getProducts(request: String): Flow<Response<ProductResponse>> {
        return flow
    }
}
