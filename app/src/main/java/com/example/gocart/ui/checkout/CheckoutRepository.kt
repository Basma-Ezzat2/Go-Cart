package com.example.gocart.ui.checkout

import android.app.Application
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.OrderObject
import com.example.gocart.retrofit.RetrofitBuilder.apiService

class CheckoutRepository (val application: Application, var sharedPref: SharedPreferencesProvider) {

    suspend fun saveOrderList(orderItem: OrderObject) {
        apiService.createOrder(sharedPref.getSettings().customer!!.customerId!!,orderItem)
    }
}