package com.example.shop_app.ui.view.screen.product_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.components.Response
import com.example.shop_app.domain.usecase.GetProductUseCase
import com.example.shop_app.domain.usecase.GetRecentlyViewedProductsUseCase
import com.example.shop_app.ui.view.global_components.RecentlyViewedProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getRecentlyViewedProductsUseCase: GetRecentlyViewedProductsUseCase
) : ViewModel() {

    private val _productListState = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _productListState

    private val _recentlyViewedProductsState = mutableStateOf(RecentlyViewedProductsState())
    val recentlyViewedProductsState: State<RecentlyViewedProductsState> =
        _recentlyViewedProductsState

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.localizedMessage?.let {
            _productListState.value = ProductListState(error = it)
        }
    }

    init {
        onEvent(Event.LoadRVP)
    }

    private fun getProducts(searchQuery: String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getProductUseCase.getProducts(searchQuery).onEach { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _productListState.value = ProductListState(productList = it)
                        }
                    }
                    is Response.Error -> {
                        response.message?.let {
                            _productListState.value = ProductListState(error = it)
                        }
                    }
                    is Response.Loading -> {
                        _productListState.value = ProductListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getRecentlyViewedProducts() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getRecentlyViewedProductsUseCase.getRecentlyViewedProducts().onEach { response ->
                if (response.isNotEmpty()) {
                    _recentlyViewedProductsState.value = RecentlyViewedProductsState(
                        recentlyViewedProducts = response
                    )
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.SearchProduct -> getProducts(event.query)
            is Event.ClearUI -> clearState()
            is Event.LoadRVP -> getRecentlyViewedProducts()
            is Event.ClearRVP -> clearViewedProduct()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun clearState() {
        _productListState.value = ProductListState()
    }

    private fun clearViewedProduct() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getRecentlyViewedProductsUseCase.clearViewedProducts()
            _recentlyViewedProductsState.value = RecentlyViewedProductsState()
        }
    }

    sealed class Event {

        // Event to perform search
        data class SearchProduct(val query: String) : Event()

        // Event to clear the UI state
        object ClearUI : Event()

        object LoadRVP : Event()

        object ClearRVP : Event()
    }
}
