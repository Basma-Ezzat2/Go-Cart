package com.example.gocart.ui.cart

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository

class CartViewModel(application: Application, val authenticationRepo: AuthRepo) : AndroidViewModel(application) {

    val repo = RoomRepository(RoomDataBase.getInstance(application))

    fun getAllData() = repo.getAllCartList()
    suspend fun updateCard( p : ProductCartModule)  = repo.saveCartItem(p)

    suspend fun deleteCartItem(product : ProductCartModule) = repo.deleteOnCartItem(product.id)

    suspend fun deleteAllCart() = repo.deleteAllFromCart()

    class Factory(
        private val application: Application,
        private val authRepo: AuthRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CartViewModel(application, authRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): CartViewModel {
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
            )[CartViewModel::class.java]
        }
    }
}