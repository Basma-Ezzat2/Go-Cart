package com.example.gocart.ui.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gocart.retrofit.RetrofitBuilder.apiService
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository

class ConfirmPaymentViewModel(application: Application) : AndroidViewModel(application) {

    val repo = RoomRepository(RoomDataBase.getInstance(application))

    fun getAllData() = repo.getAllCartList()

    suspend fun getDiscount (title  : String) = apiService.getAllDiscounts().body()?.discount?.firstOrNull {
        it.title == title
    }
}