package com.example.gocart.currencyretrofit

import com.example.gocart.currencypojo.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("EGP")
    suspend fun getRates(  ) : Response<CurrencyResponse>
}