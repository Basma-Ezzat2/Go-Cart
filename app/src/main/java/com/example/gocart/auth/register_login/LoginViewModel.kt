package com.example.gocart.auth.register_login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gocart.auth.repositories.AuthenticationRepo

import kotlinx.coroutines.launch

class LoginViewModel(application: Application, val AuthRepo: AuthenticationRepo): AndroidViewModel(application) {
//    val loginSuccess: MutableLiveData<Boolean?> = MutableLiveData()
//
//    fun getData(email: String){
//        viewModelScope.launch{
//            val response: Either<CustomerLoginModel, LoginErrors> = AuthRepo.login(email)
//            when(response){
//                is Either.Error -> when(response.errorCode){
//                    LoginErrors.NoInternetConnection ->{
//                        Toast.makeText(getApplication(), "NO INTERNET CONNECTION"+response.message, Toast.LENGTH_LONG).show()
//                    }
//                    LoginErrors.ServerError ->{
//                        Toast.makeText(getApplication(), "Server Error"+response.message,Toast.LENGTH_LONG).show()
//                    }
//                    LoginErrors.CustomerNotFound ->{
//                        Toast.makeText(getApplication(), "Customer Not Found"+response.message, Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//                is Either.Success ->{
//                    loginSuccess.postValue(true)
//                }
//            }
//        }
//    }
}