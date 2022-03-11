package com.example.gocart.ui.checkout

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.Address
import com.example.gocart.pojo.AddressModel
import com.example.gocart.pojo.AddressModelT
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.settings.address.AddressViewModel

class ChooseAddressAndPaymentViewModel (val authenticationRepo: AuthRepo, application: Application) : AndroidViewModel(application) {


    class Factory(private val application: Application, val AuthRepo: AuthRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChooseAddressAndPaymentViewModel(AuthRepo, application) as T
        }
    }

    companion object {
        fun create(context: Fragment): ChooseAddressAndPaymentViewModel {
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
            )[ChooseAddressAndPaymentViewModel::class.java]
        }
    }


    suspend fun getCustomerAddresses() =
        authenticationRepo.getAddresses(authenticationRepo.sharedPref.getSettings().customer!!.customerId!!)
}
