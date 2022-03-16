package com.example.gocart.ui.wishList

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository
import com.example.gocart.ui.cart.CartViewModel
import com.example.gocart.ui.home.pojo.productdetail.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class WishListViewModel( application: Application,  val authenticationRepo: AuthRepo) : AndroidViewModel(application) {

    val repo = RoomRepository(RoomDataBase.getInstance(application))
    fun getAllWishList(): LiveData<List<com.example.gocart.pojo.Product>> = repo.getAllWishList()
    fun deleteOneWishItem(id: Long)= viewModelScope.launch {
        repo.deleteOneWishItem(id)
    }
    fun updateCard( p : ProductCartModule)  = viewModelScope.launch {
        repo.saveCartItem(p)}



    class Factory(
        private val application: Application,
        private val authRepo: AuthRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WishListViewModel(application, authRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): WishListViewModel {
            return ViewModelProvider(
                context,
                Factory(
                    context.context?.applicationContext as Application,
                    AuthRepo(
                        RetrofitBuilder.apiService,
                        SharedPreferencesProvider(context.context?.applicationContext as Application),
                        context.context?.applicationContext as Application
                    )
                )
            )[WishListViewModel::class.java]
        }
    }


}