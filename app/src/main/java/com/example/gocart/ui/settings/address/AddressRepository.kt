package com.example.gocart.ui.settings.address

import android.app.Application
import com.example.gocart.pojo.Address
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.auth.utils.Connectivity
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.pojo.AddressModel
import com.example.gocart.pojo.AddressModelT
import com.example.gocart.retrofit.RetrofitBuilder

class AddressRepository (val application: Application, var sharedPref: SharedPreferencesProvider){

    suspend fun getAddresses (id : Long) : Either<List<Address>, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = RetrofitBuilder.apiService.getAddress(id)
                if (res.isSuccessful) {

                    return Either.Success(res.body()?.customer?.addresses!!)
                } else
                    return Either.Error(LoginErrors.ServerError, res.message())
            } else
                return Either.Error(LoginErrors.NoInternetConnection, "NoInternetConnection")

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }

    suspend fun addAddresses (id : Long, address: AddressModel) : Either<Unit, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = RetrofitBuilder.apiService.addAddress(id,address)
                if (res.isSuccessful) {

                    return Either.Success(Unit)
                } else
                    return Either.Error(LoginErrors.ServerError, res.message())
            } else
                return Either.Error(LoginErrors.NoInternetConnection, "NoInternetConnection")

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }

    suspend fun deleteAddresses (id : Long, address: AddressModelT) : Either<Unit, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = RetrofitBuilder.apiService.deleteAddress(id,address.address?.id!!)
                if (res.isSuccessful) {

                    return Either.Success(Unit)
                } else
                    return Either.Error(LoginErrors.ServerError, res.message())
            } else
                return Either.Error(LoginErrors.NoInternetConnection, "NoInternetConnection")

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }

}