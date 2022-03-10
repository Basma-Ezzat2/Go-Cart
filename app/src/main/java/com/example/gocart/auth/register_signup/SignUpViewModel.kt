package com.example.gocart.auth.register_signup

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.RepoErrors
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.retrofit.RetrofitBuilder

import kotlinx.coroutines.launch

class SignUpViewModel(application: Application, private val authenticationRepo: AuthRepo) :
    AndroidViewModel(application) {

    val signupSuccess: MutableLiveData<Boolean?> = MutableLiveData()

    @RequiresApi(Build.VERSION_CODES.M)
    fun postData(customer: CustomerModel) {
        viewModelScope.launch {
            when (val response: Either<CustomerModel, RepoErrors> =
                authenticationRepo.signUp(customer)) {
                is Either.Error -> when (response.errorCode) {
                    RepoErrors.NoInternetConnection -> {
                        Toast.makeText(
                            getApplication(),
                            "NoInternetConnection" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    RepoErrors.ServerError -> {

                        Toast.makeText(
                            getApplication(),
                            "ServerError" + response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Either.Success -> signupSuccess.postValue(true)
            }
        }
    }


    class Factory(
        private val application: Application,
        val authenticationRepo: AuthRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignUpViewModel(application, authenticationRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): SignUpViewModel {
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
            )[SignUpViewModel::class.java]
        }
    }

}