package com.example.shop_app.ui.view.screen.product_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.components.Response
import com.example.shop_app.components.Utils
import com.example.shop_app.domain.usecase.GetProductDetailsUseCase
import com.example.shop_app.domain.usecase.GetRecentlyViewedProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val getRecentlyViewedProductsUseCase: GetRecentlyViewedProductsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _productDetailsState = mutableStateOf(ProductDetailsState())
    val state: State<ProductDetailsState> = _productDetailsState

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.localizedMessage?.let {
            _productDetailsState.value = ProductDetailsState(error = it)
        }
    }

    init {
        val productId = savedStateHandle.get<String>("productId")
        getProductDetails(productId ?: Utils.DEFAULT_PRODUCT_ID)
    }

    private fun getProductDetails(productId: String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getProductDetailsUseCase.getProductDetails(productId).onEach { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _productDetailsState.value = ProductDetailsState(productDetails = it)
                        }
                        saveViewedProduct()
                    }
                    is Response.Error -> {
                        response.message?.let {
                            _productDetailsState.value = ProductDetailsState(error = it)
                        }
                    }
                    is Response.Loading -> {
                        _productDetailsState.value = ProductDetailsState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private suspend fun saveViewedProduct() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getRecentlyViewedProductsUseCase.saveViewedProduct(
                _productDetailsState.value.productDetails
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
