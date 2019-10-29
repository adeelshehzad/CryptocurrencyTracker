package com.adeel.cryptocurrencytracker.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.adeel.cryptocurrencytracker.mvvm.model.CryptoCurrency

class CryptoCurrencyVM(application: Application) : AndroidViewModel(application) {
    val cryptoCurrencyRepo = CryptoCurrencyRepo()
    val cryptoCurrencyData : LiveData<CryptoCurrency> = cryptoCurrencyRepo.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        cryptoCurrencyRepo.completeJob.cancel()
    }
}