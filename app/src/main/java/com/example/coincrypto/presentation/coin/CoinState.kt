package com.example.coincrypto.presentation.coin

import com.example.coincrypto.domain.model.CoinDetail

data class CoinState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? =null,
    val error: String = ""
)