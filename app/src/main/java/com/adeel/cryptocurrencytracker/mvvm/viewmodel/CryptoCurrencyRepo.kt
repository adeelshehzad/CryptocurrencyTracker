package com.adeel.cryptocurrencytracker.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.adeel.cryptocurrencytracker.mvvm.model.CryptoCurrency
import com.adeel.cryptocurrencytracker.mvvm.networking.RestAPIService
import kotlinx.coroutines.*
import retrofit2.HttpException

class CryptoCurrencyRepo() {
    private var cryptoCurrency = mutableListOf<CryptoCurrency>()
    private var mutableCryptoCurrency = MutableLiveData<CryptoCurrency>()
    val completeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completeJob)

    private val thisApiCorService by lazy {
        RestAPIService.createCorService()
    }

    fun getMutableLiveData(): MutableLiveData<CryptoCurrency> {
        coroutineScope.launch {
            val request = thisApiCorService.getCryptoCurrencyData()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    val cryptoCurrency = response
                    mutableCryptoCurrency.value = cryptoCurrency

                } catch (e: HttpException) {
                    Log.e("ERROR_TAG", "Error", e)

                } catch (e: Throwable) {
                    Log.e("ERROR_TAG", "Error", e)
                }
            }
        }
        return mutableCryptoCurrency
    }
}