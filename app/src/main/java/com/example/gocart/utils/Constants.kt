package com.example.gocart.utils

import com.example.gocart.currencyretrofit.RetroBuilder

object Constants {

    // sharedPreferences Tags
    const val ALL_DATA_ROUTE = "ALL_DATA_ROUTE"

    //api
    const val apiKey = "bfe73f4cd7e7f8737d5928b2a439022e"
    const val password = "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"
    const val BASE_URL = "https://$apiKey:$password@jets2022.myshopify.com/admin/api/2022-01/"



    // currency api

    const val CURRENCY_BASE_URL = "https://v6.exchangerate-api.com/v6/e2d6df5192509a9af9724757/latest/"
    const val currencyApiKey = "e2d6df5192509a9af9724757"

    suspend fun convertPrice ( price : Double) {
        RetroBuilder.currencyApiService.getRates()
    }


}