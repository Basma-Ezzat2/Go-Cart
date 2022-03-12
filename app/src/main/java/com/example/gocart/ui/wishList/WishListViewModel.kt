package com.example.gocart.ui.wishList

import android.app.Application
import androidx.lifecycle.*
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository
import com.example.gocart.ui.home.pojo.productdetail.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class WishListViewModel( application: Application) : AndroidViewModel(application) {

    val repo = RoomRepository(RoomDataBase.getInstance(application))
    fun getAllWishList(): LiveData<List<com.example.gocart.pojo.Product>> = repo.getAllWishList()
    fun deleteOneWishItem(id: Long)= viewModelScope.launch {
        repo.deleteOneWishItem(id)
    }
    fun updateCard( p : ProductCartModule)  = viewModelScope.launch {
        repo.saveCartItem(p)}




}