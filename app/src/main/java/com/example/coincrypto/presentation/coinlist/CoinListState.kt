package com.example.coincrypto.presentation.coinlist

import com.example.coincrypto.domain.model.Coin

data class CoinListState(
    val isLoading  : Boolean = false,
    val coinList : List<Coin> = emptyList(),
    val error : String = ""

)
