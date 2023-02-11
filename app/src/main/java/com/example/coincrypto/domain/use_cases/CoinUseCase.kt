package com.example.coincrypto.domain.use_cases

import com.example.coincrypto.domain.model.CoinDetail
import com.example.coincrypto.domain.repository.CoinRepository
import com.example.coincrypto.utils.ResponseState
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinUseCase @Inject constructor(
    private val repository : CoinRepository
){
    operator fun invoke(id: String): kotlinx.coroutines.flow.Flow<ResponseState<List<CoinDetail>>> =
        flow {
            try {
                emit(ResponseState.Loading<List<CoinDetail>>())
                val coinDetail = repository.getCoinById(id).toCoinDetail()


                emit(ResponseState.Success<List<CoinDetail>>(listOf(coinDetail)))
            } catch (e: HttpException) {
                emit(ResponseState.Error<List<CoinDetail>>(e.localizedMessage ?: "An Unexpected Error"))
            } catch (e: IOException) {
                emit(ResponseState.Error<List<CoinDetail>>("Internet Error"))
            }
        }
}