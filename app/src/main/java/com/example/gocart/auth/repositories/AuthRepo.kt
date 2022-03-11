package com.example.gocart.auth.repositories

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.gocart.auth.pojo.Address
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.pojo.CustomersModel
import com.example.gocart.auth.sharedpreferences.*
import com.example.gocart.auth.utils.Connectivity
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.auth.utils.RepoErrors
import com.example.gocart.pojo.AddressModel
import com.example.gocart.pojo.AddressModelT
import com.example.gocart.retrofit.ApiService
import com.example.gocart.retrofit.RetrofitBuilder.apiService


class AuthRepo(
    val ShopifyServices: ApiService,
    var sharedPref: SharedPreferencesProvider,
    val application: Application
) {


    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun signUp(customer: CustomerModel): Either<CustomerModel, RepoErrors> {
        return try {
            return if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.register(customer)
                if (res.isSuccessful) {
                    sharedPref.update {
                        it.copy(customer = res.body()?.customer)
                    }
                    Log.d("customerINFO",res.body()?.customer.toString())


                    Log.d("body", res.body()?.customer.toString())

                    Either.Success(res.body()!!)
                } else
                    Either.Error(RepoErrors.ServerError, res.message())
            } else
                Either.Error(RepoErrors.NoInternetConnection, "NoInternetConnection")

        } catch (t: Throwable) {
            Either.Error(RepoErrors.ServerError, t.message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun signIn(email: String): Either<CustomersModel, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.login()
                if (res.isSuccessful) {

                    val customer = res.body()?.customer?.first {
                        it?.email.equals(email)
                    } ?: return Either.Error(LoginErrors.CustomerNotFound, "CustomerNotFound")

                    sharedPref.update {
                        it.copy(
                            customer = customer
                        )
                    }
                    Log.d("signBody", res.body()!!.customer.toString())

                    return Either.Success(res.body()!!)
                } else
                    return Either.Error(LoginErrors.ServerError, res.message())
            } else
                return Either.Error(LoginErrors.NoInternetConnection, "NoInternetConnection")

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }

    suspend fun getAddresses (id : Long) : Either<List<Address>, LoginErrors>{
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.getAddress(id)
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

    suspend fun addAddresses (id : Long, address: AddressModel) : Either<Unit, LoginErrors>{
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.addAddress(id,address)
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

    suspend fun deleteAddresses (id : Long, address: AddressModelT) : Either<Unit, LoginErrors>{
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.deleteAddress(id,address.address?.id!!)
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