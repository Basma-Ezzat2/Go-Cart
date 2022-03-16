package com.example.gocart.utils

import android.util.Log
import com.example.gocart.currencyretrofit.RetroBuilder
import com.example.gocart.currencyretrofit.RetroBuilder.currencyApiService

object Constants {

    // sharedPreferences Tags
    const val ALL_DATA_ROUTE = "ALL_DATA_ROUTE"
    var IS_LOGIN = false

    var isUSD = true

    //api
    const val apiKey = "f36da23eb91a2fd4cba11b9a30ff124f"
    const val password = "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"
    const val BASE_URL = "https://$apiKey:$password@jets-ismailia.myshopify.com/admin/api/2022-01/"



    // currency api

    const val CURRENCY_BASE_URL = "https://v6.exchangerate-api.com/v6/e2d6df5192509a9af9724757/"
    const val currencyApiKey = "e2d6df5192509a9af9724757"

    suspend fun convertPrice ( price : Double?) : String {
        if (isUSD){
            return  ""+price+" USD"
        }else{
            return  ""+((price?:0.0)*15.70)+" EGP"//""+((RetroBuilder.currencyApiService.getCurrenciesValueNow().body()?.conversion_rates?.EGP)?:0.0)*(price?:0.0)+" EGP"
        }
    }


}