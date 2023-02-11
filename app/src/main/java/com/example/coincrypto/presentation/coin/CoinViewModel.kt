package com.example.coincrypto.presentation.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coincrypto.domain.use_cases.CoinUseCase
import com.example.coincrypto.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase
) :ViewModel(){

    private val coinValue = MutableStateFlow(CoinState())
    var _coinValue : StateFlow<CoinState> = coinValue

    fun getCoinById(id:String)=viewModelScope.launch(Dispatchers.IO){
        coinUseCase(id = id).collect {
            when(it){
                is ResponseState.Success ->{
                    coinValue.value = CoinState(coinDetail = it.data?.firstOrNull())
                }
                is ResponseState.Loading ->{
                    coinValue.value = CoinState(isLoading = true)
                }
                is ResponseState.Error ->{
                    coinValue.value = CoinState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}