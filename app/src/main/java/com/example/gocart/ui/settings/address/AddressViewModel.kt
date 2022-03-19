package com.example.gocart.ui.settings.address

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.register_login.SignInViewModel
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.pojo.Address
import com.example.gocart.pojo.AddressModel
import com.example.gocart.pojo.AddressModelT
import com.example.gocart.retrofit.RetrofitBuilder

class AddressViewModel(val addressRepository: AddressRepository, application: Application) : AndroidViewModel(application) {



    class Factory(private val application: Application, val AuthRepo: AuthRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(AddressRepository(application,SharedPreferencesProvider(application)),application) as T
        }
    }

    companion object {
        fun create(context: Fragment): AddressViewModel {
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
            )[AddressViewModel::class.java]
        }
    }

    suspend fun addAddress(address: Address) = addressRepository.addAddresses(addressRepository.sharedPref.getSettings().customer!!.customerId!!, AddressModel(address))
    suspend fun deleteAddress(address: Address) = addressRepository.deleteAddresses(addressRepository.sharedPref.getSettings().customer!!.customerId!!, AddressModelT(address))
    suspend fun getCustomerAddresses() = addressRepository.getAddresses(addressRepository.sharedPref.getSettings().customer!!.customerId!!)

}