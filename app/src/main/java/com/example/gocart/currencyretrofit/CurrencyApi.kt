package com.example.gocart.currencyretrofit

import com.example.gocart.currencypojo.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest/USD")
    suspend fun getCurrency(): Response<CurrencyResponse>
}