package com.example.shop_app.di

import android.content.Context
import androidx.room.Room
import com.example.shop_app.components.Utils
import com.example.shop_app.data.network.ProductApi
import com.example.shop_app.data.repository.ProductRepository
import com.example.shop_app.data.repository.RecentlyViewedProductsRepository
import com.example.shop_app.data.repository.db.AppDatabase
import com.example.shop_app.data.repository.db.RecentlyViewedTypeConverter
import com.example.shop_app.data.repository.db.dao.RecentlyViewedProductsDao
import com.example.shop_app.data.usecase.GetProductDetailsUseCaseImpl
import com.example.shop_app.data.usecase.GetProductUseCaseImpl
import com.example.shop_app.data.usecase.GetRecentlyViewProductsUseCaseImpl
import com.example.shop_app.domain.usecase.GetProductDetailsUseCase
import com.example.shop_app.domain.usecase.GetProductUseCase
import com.example.shop_app.domain.usecase.GetRecentlyViewedProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        val httpClient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCase(repository: ProductRepository): GetProductUseCase {
        return GetProductUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetProductDetailsUseCase(repository: ProductRepository): GetProductDetailsUseCase {
        return GetProductDetailsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecentlyViewedProductsUseCase(repository: RecentlyViewedProductsRepository):
        GetRecentlyViewedProductsUseCase {
        return GetRecentlyViewProductsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesRecentlyViewedProductsDao(@ApplicationContext context: Context):
        RecentlyViewedProductsDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Utils.db_name
        )
            .addTypeConverter(RecentlyViewedTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
            .recentlyViewedProductsDao()
    }
}
