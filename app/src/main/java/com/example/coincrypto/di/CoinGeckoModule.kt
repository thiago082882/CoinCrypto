package com.example.coincrypto.di

import com.example.coincrypto.data.data_source.CoinGeckoApi
import com.example.coincrypto.data.repository.CoinRepositoryImpl
import com.example.coincrypto.domain.repository.CoinRepository
import com.example.coincrypto.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {

    @Provides
    @Singleton
    fun provideJokesApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinGeckoRepository(api:CoinGeckoApi): CoinRepository {
        return CoinRepositoryImpl(api=api)
    }
}