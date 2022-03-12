package com.example.gocart.ui.order

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.*
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.settings.address.AddressViewModel

class OrderViewModel (val authenticationRepo: AuthRepo, application: Application) : AndroidViewModel(application) {


    class Factory(private val application: Application, val AuthRepo: AuthRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrderViewModel(AuthRepo, application) as T
        }
    }

    companion object {
        fun create(context: Fragment): OrderViewModel {
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
            )[OrderViewModel::class.java]
        }
    }

    suspend fun createOrder(order: Orders) = authenticationRepo.createOrder(authenticationRepo.sharedPref.getSettings().customer!!.customerId!!, order)

}