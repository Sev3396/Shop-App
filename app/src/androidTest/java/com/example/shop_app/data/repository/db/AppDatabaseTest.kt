package com.example.shop_app.data.repository.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import com.example.shop_app.data.repository.db.dao.RecentlyViewedProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {

    @get: Rule
    val taskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: RecentlyViewedProductsDao
    private val recentlyViewedTypeConverter = RecentlyViewedTypeConverter()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .addTypeConverter(recentlyViewedTypeConverter)
            .build()

        dao = database.recentlyViewedProductsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun saveViewedProduct() = runTest {
        withContext(Dispatchers.IO) {
            val product = getRecentlyViewedProductStub()
            dao.insertProduct(product)
            advanceUntilIdle()

            dao.getRecentlyViewedProducts().test {
                val items = awaitItem()
                advanceUntilIdle()
                assert(items.contains(product))
            }
        }
    }

    @Test
    fun clearViewedProduct() = runTest {
        withContext(Dispatchers.IO) {
            val product = getRecentlyViewedProductStub()
            dao.insertProduct(product)
            advanceUntilIdle()

            dao.clearViewedProducts()
            advanceUntilIdle()

            dao.getRecentlyViewedProducts().test {
                val list = awaitItem()
                advanceUntilIdle()
                assert(list.isEmpty())
            }
        }
    }
}
