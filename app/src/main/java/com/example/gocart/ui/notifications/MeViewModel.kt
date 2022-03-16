package com.example.gocart.ui.notifications

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.OrderObject
import com.example.gocart.pojo.Product
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository
import kotlinx.coroutines.launch

class MeViewModel (
    application: Application,
    val authenticationRepo: AuthRepo

) : AndroidViewModel(application) {
    val repo = RoomRepository(RoomDataBase.getInstance(application))

    fun getFourFromWishList(): LiveData<List<Product>> = repo.getFourFromWishList()
    fun getTwoFromOrderList(): LiveData<List<OrderObject>> = repo.getTwoFromOrderList()


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
            return MeViewModel(application, authRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): MeViewModel {
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
            )[MeViewModel::class.java]
        }
    }
}