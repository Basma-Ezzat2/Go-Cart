package com.example.gocart.currencyretrofit

import com.example.gocart.currencypojo.ConversionRates
import com.example.gocart.currencypojo.CurrencyResponse
import com.example.gocart.utils.Constants.currencyApiKey
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

   /* @GET("latest/USD")
    suspend fun getCurrency(): Response<CurrencyResponse>*/

    @GET("/api/v7/convert?q=USD_EGP&compact=ultra")
    suspend fun getCurrenciesValueNow(
        @Query("apiKey") apiKey:String = currencyApiKey
    ): Response<ConversionRates>
}