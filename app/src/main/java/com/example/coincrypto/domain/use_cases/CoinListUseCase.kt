package com.example.coincrypto.domain.use_cases

import com.example.coincrypto.domain.model.Coin
import com.example.coincrypto.domain.repository.CoinRepository
import com.example.coincrypto.utils.ResponseState
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class CoinListUseCase @Inject constructor(

    private val repository : CoinRepository

) {

    operator fun invoke(page:String): kotlinx.coroutines.flow.Flow<ResponseState<List<Coin>>> = flow {
        try {
            emit(ResponseState.Loading<List<Coin>>())
            val coins = repository.getAllCoins(page).map {
                it.toCoin()
            }
            emit(ResponseState.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected Error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<Coin>>("Internet Error"))
        }
    }
}