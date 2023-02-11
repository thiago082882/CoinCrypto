package com.example.coincrypto.presentation.coinlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coincrypto.domain.use_cases.CoinListUseCase
import com.example.coincrypto.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CoinListViewModel @Inject constructor(

    private val coinsUseCase: CoinListUseCase,

    ) : ViewModel() {
    private val coinListValue = MutableStateFlow(CoinListState())
    var _coinListValue: StateFlow<CoinListState> = coinListValue

    fun getAllCoins(page: String) = viewModelScope.launch(Dispatchers.IO) {
        coinsUseCase(page).collect {
            when (it) {
                is ResponseState.Success -> {
                coinListValue.value = CoinListState(coinList = it.data?:emptyList())
                }
                is ResponseState.Loading -> {

                    coinListValue.value = CoinListState(isLoading = true)

                }
                is ResponseState.Error -> {

                    coinListValue.value = CoinListState(error = it.message?:"An Unexpected Error")

                }
            }
        }
    }
}
