package com.example.gocart.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val repo = RoomRepository(RoomDataBase.getInstance(application))

    fun getAllData() = repo.getAllCartList()
    suspend fun updateCard( p : ProductCartModule)  = repo.saveCartItem(p)

    suspend fun deleteCartItem(product : ProductCartModule) = repo.deleteOnCartItem(product.id)
}