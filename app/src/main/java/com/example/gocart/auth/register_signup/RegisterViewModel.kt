package com.example.gocart.auth.register_signup

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.repositories.AuthenticationRepo
import com.example.gocart.auth.repositories.RepoErrors
import com.example.gocart.auth.sharedpreferences.SettingsPreferences
import com.example.gocart.auth.utils.Either
import com.example.gocart.retrofit.RetrofitBuilder
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application, val authenticationRepo: AuthenticationRepo ): AndroidViewModel(application){
    val signupSuccess: MutableLiveData<Boolean?> = MutableLiveData()

    fun postData(customer:CustomerModel){
        viewModelScope.launch{
            val response : Either<CustomerModel, RepoErrors> = authenticationRepo.signUp(customer)


            when(response){
                is Either.Error -> when(response.errorCode){
                    RepoErrors.NoInternetConnection -> {
                        Toast.makeText(getApplication(),"No INTERNET CONNECTION"+response.message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Either.Success ->{
                    signupSuccess.postValue(true)

                }
            }
        }
    }
}

//class Factory(private val application: Application, val authenticationRepo: AuthenticationRepo):ViewModelProvider.Factory{
//    override fun <T:ViewModel> create(modelClass: Class<T>):T{
//        return RegisterViewModel(application, authenticationRepo) as T
//    }
//}
//companion object {
//    fun create(context:Fragment):RegisterViewModel{
//        return ViewModelProvider{
//            context,
//                Factory(
//                    context.context?.applicationContext as Application, AuthenticationRepo(
//                      RetrofitBuilder.api,
//                        SettingsPreferences()
//                    )
//                )
//
//        }
//    }
//}