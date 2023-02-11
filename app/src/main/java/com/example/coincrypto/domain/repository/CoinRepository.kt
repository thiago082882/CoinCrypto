package com.example.coincrypto.domain.repository

import com.example.coincrypto.data.data_source.dto.CoinDetailDTO.CoinDetailDto
import com.example.coincrypto.data.data_source.dto.CoinListDTO.CoinListDto

interface CoinRepository {

    suspend fun getAllCoins(page: String): CoinListDto
    suspend fun getCoinById(id: String): CoinDetailDto
}