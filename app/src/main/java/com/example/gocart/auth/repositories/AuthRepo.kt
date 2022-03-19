package com.example.gocart.auth.repositories

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.gocart.auth.pojo.Address
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.pojo.CustomersModel
import com.example.gocart.auth.sharedpreferences.*
import com.example.gocart.auth.utils.Connectivity
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.auth.utils.RepoErrors
import com.example.gocart.pojo.*
import com.example.gocart.retrofit.ApiService
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.retrofit.RetrofitBuilder.apiService
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AuthRepo(
    val ShopifyServices: ApiService,
    var sharedPref: SharedPreferencesProvider,
    val application: Application
) {
    val repo = RoomRepository(RoomDataBase.getInstance(application))


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
    suspend fun signIn(email: String, pass: String): Either<CustomersModel, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val res = apiService.login()
                if (res.isSuccessful) {

                    val customer = res.body()?.customer?.first {
                        it?.email.equals(email)
                    } ?: return Either.Error(LoginErrors.UserNotFound, "User Not Found")
                    if (customer.lastName.equals(pass)) {
                        sharedPref.update {
                            it.copy(
                                customer = customer
                            )
                        }
                    } else return Either.Error(
                        LoginErrors.IncorrectPassword,
                        "Please Insert Correct email or password"
                    )

                    return Either.Success(res.body()!!)
                } else
                    return Either.Error(LoginErrors.ServerError, res.message())
            } else
                return Either.Error(LoginErrors.NoInternetConnection, "Connection Filed")

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }


    suspend fun createOrder (id : Long, orders: OrderObject) : Either<Unit, LoginErrors> {
        return try {
            if (Connectivity.isOnline(application.applicationContext)) {
                val response = apiService.createOrder(id, orders)
                if (response.isSuccessful) {

                    return Either.Success(Unit)    //postValue(response.body())
                    // return response.body()
                } else {
                    return Either.Error(LoginErrors.ServerError, response.message())
                }
            } else {
                return Either.Error(LoginErrors.NoInternetConnection,"NoInternetConnection")
            }
        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }





}