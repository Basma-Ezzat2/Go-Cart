package com.example.gocart.currencyretrofit

import com.example.gocart.retrofit.ApiService
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.utils.Constants
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetroBuilder {

    val retrofit2: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.CURRENCY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var currencyApiService: CurrencyApi = retrofit2.create(CurrencyApi::class.java)


}