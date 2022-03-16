package com.example.gocart.auth.register_login

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.gocart.auth.pojo.CustomersModel
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.retrofit.ApiService
import com.example.gocart.retrofit.RetrofitBuilder.apiService

import kotlinx.coroutines.launch


class SignInViewModel(application: Application, val AuthRepo: AuthRepo) :
    AndroidViewModel(application) {

    val loginSuccess: MutableLiveData<Boolean?> = MutableLiveData()


    @RequiresApi(Build.VERSION_CODES.M)
    fun getData(email: String,pass:String) {
        viewModelScope.launch {

            when (val response: Either<CustomersModel, LoginErrors> = AuthRepo.signIn(email,pass)) {
                is Either.Error -> when (response.errorCode) {
                    LoginErrors.ConnectionFiled -> {
                        Toast.makeText(
                            getApplication(),
                            "NoInternetConnection" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginErrors.ServerError -> {

                        Toast.makeText(
                            getApplication(),
                            "ServerError" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginErrors.IncorrectPassword->{
                        Toast.makeText(
                            getApplication(),
                            "IncorrectEmailOrPassword" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginErrors.UserNotFound -> {
                        Toast.makeText(
                            getApplication(),
                            "CustomerNotFound" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Either.Success -> {
                    Log.d("singin", "" + response.data)
                    loginSuccess.postValue(true)
                }
            }
        }
    }


    class Factory(private val application: Application, val AuthRepo: AuthRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInViewModel(application, AuthRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): SignInViewModel {
            return ViewModelProvider(
                context,
                Factory(
                    context.context?.applicationContext as Application,
                    AuthRepo(
                        apiService,
                        SharedPreferencesProvider(context.context?.applicationContext as Application),

                        context.context?.applicationContext as Application
                    )
                )
            )[SignInViewModel::class.java]
        }
    }
}