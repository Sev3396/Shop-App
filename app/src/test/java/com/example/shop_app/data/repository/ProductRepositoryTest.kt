package com.example.shop_app.data.repository

import com.example.shop_app.components.Response
import com.example.shop_app.view.getProductResponseStub
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private val productRepository: ProductRepository = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `WHEN api called on success response THEN same should be returned`() = runTest {
        coEvery { productRepository.execute(any()) } returns flow {
            emit(Response.Success(getProductResponseStub().copy(total = 5)))
        }
        productRepository.execute("bag").collect { response ->
            assertEquals(5, response.data?.total)
        }
    }

    @Test
    fun `WHEN api called on exception occurs THEN error should be thrown`() = runTest {
        val exception = RuntimeException("exception")
        coEvery { productRepository.execute(any()) } returns flow {
            throw exception
        }
        productRepository.execute("bag").catch { cause ->
            assertEquals("exception", cause.message)
        }.collect()
    }

    @Test
    fun `WHEN api called on loading occurs THEN loading state should be shown`() = runTest {
        coEvery { productRepository.execute(any()) } returns flow {
            emit(Response.Loading())
        }
        productRepository.execute("bag").collect { response ->
            assertTrue(response is Response.Loading)
        }

    }
}
