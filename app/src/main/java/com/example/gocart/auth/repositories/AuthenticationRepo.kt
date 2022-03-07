package com.example.gocart.auth.repositories

import android.app.Application
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.utils.Either
import com.example.gocart.retrofit.RetrofitBuilder.api
import com.stash.shopeklobek.model.entities.CustomerLoginModel

class AuthenticationRepo(
    val application: Application
) {




    suspend fun signUp(customer: CustomerModel): Either<CustomerModel, RepoErrors> {
        return try {
            val res =api.register(customer)
            if (res.isSuccessful){
                return Either.Success(res.body()!!)
            }else{
                return Either.Error(RepoErrors.ServerError)

            }

        } catch (t: Throwable) {
            Either.Error(RepoErrors.ServerError,t.message)
        }
    }

    suspend fun login(email: String): Either<CustomerLoginModel, LoginErrors> {
        return try {
            val res = api.login()
            if (res.isSuccessful) {

                val customer = res.body()?.customer?.first {
                    it?.email.equals(email)
                } ?: return Either.Error(LoginErrors.CustomerNotFound,"CustomerNotFound")
                return Either.Success(res.body()!!)
            } else {
                return Either.Error(LoginErrors.ServerError)

            }

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }


}