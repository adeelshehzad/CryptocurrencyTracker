package com.adeel.cryptocurrencytracker.mvvm.networking

import com.adeel.cryptocurrencytracker.mvvm.model.CryptoCurrency
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RestAPIService {
    @GET("v1/cryptocurrency/listings/latest")
    fun getCryptoCurrencyData(): Deferred<CryptoCurrency>

    companion object {
        fun createCorService(): RestAPIService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()

                //Add API_KEY in the .header() func
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }

            val client = httpClient.build()

            return Retrofit.Builder()
                .baseUrl("https://pro-api.coinmarketcap.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RestAPIService::class.java)
        }
    }
}