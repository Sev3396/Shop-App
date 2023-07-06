package com.example.shop_app.view

import com.example.shop_app.components.Response
import com.example.shop_app.domain.usecase.GetProductUseCase
import com.example.shop_app.domain.usecase.GetRecentlyViewedProductsUseCase
import com.example.shop_app.ui.view.screen.product_search.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @RelaxedMockK
    private lateinit var getProductUseCase: GetProductUseCase

    @RelaxedMockK
    private lateinit var getRecentlyViewedProductsUseCase: GetRecentlyViewedProductsUseCase

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MainViewModel(getProductUseCase, getRecentlyViewedProductsUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN ProductScreen WHEN api called clicked THEN viewModel state should be Loading`() =
        runTest {
            val usecaseStub = GetProductUseCaseStub()
            coEvery { getProductUseCase.getProducts(any()) } returns flow {
                getProductResponseStub()
            }
            backgroundScope.launch(testDispatcher) {
                usecaseStub.getProducts("bag").onEach {
                    assertTrue(it is Response.Loading)
                }.collect()
            }
            usecaseStub.emit(Response.Loading())
            testDispatcher.scheduler.advanceUntilIdle()
        }
}
