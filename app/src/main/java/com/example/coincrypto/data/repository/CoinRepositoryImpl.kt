package com.example.coincrypto.data.repository

import com.example.coincrypto.data.data_source.CoinGeckoApi
import com.example.coincrypto.data.data_source.dto.CoinDetailDTO.CoinDetailDto

import com.example.coincrypto.data.data_source.dto.CoinListDTO.CoinListDto
import com.example.coincrypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinGeckoApi
) : CoinRepository{
    override suspend fun getAllCoins(page:String): CoinListDto {
        return api.getAllCoins(page=page)
    }

    override suspend fun getCoinById(id: String): CoinDetailDto {

        return api.getCoinById(id=id)
    }
}