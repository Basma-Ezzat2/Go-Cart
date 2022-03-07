package com.example.gocart.auth.repositories

import android.app.Application

import com.example.gocart.retrofit.ApiService


class AuthenticationRepo(
    val ShopifyServices: ApiService,
//    val settingsPreferences: SettingsPreferences,
    val application: Application
) {



//
//    suspend fun signUp(customer: CustomerModel): Either<CustomerModel, RepoErrors> {
//        return try {
//            return if (NetworkingHelper.hasInternet(application.applicationContext)) {
//                val res =api.register(customer)
//                if (res.isSuccessful) {
//                    settingsPreferences.update {
//                        it.copy(
//                            customer = res.body()?.customer
//                        )
//                    }
//
//                    Either.Success(res.body()!!)
//                } else
//                    Either.Error(RepoErrors.ServerError,res.message())
//            } else
//                Either.Error(RepoErrors.NoInternetConnection,"NoInternetConnection")
//
//        } catch (t: Throwable) {
//            Either.Error(RepoErrors.ServerError,t.message)
//        }
//    }
//
//    suspend fun login(email: String): Either<CustomerLoginModel, LoginErrors> {
//        return try {
//             if (NetworkingHelper.hasInternet(application.applicationContext)) {
//                val res =api.login()
//                if (res.isSuccessful) {
//
//                    val customer = res.body()?.customer?.first {
//                        it?.email.equals(email)
//                    } ?: return Either.Error(LoginErrors.CustomerNotFound,"CustomerNotFound")
//
//                    settingsPreferences.update {
//                        it.copy(
//                            customer = customer
//                        )
//                    }
//
//                    return Either.Success(res.body()!!)
//                } else
//                    return Either.Error(LoginErrors.ServerError,res.message())
//            } else
//                 return Either.Error(LoginErrors.NoInternetConnection,"NoInternetConnection")
//
//        } catch (t: Throwable) {
//            Either.Error(LoginErrors.ServerError,t.message)
//        }
//    }


}