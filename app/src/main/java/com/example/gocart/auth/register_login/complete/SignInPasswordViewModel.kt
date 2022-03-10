package com.hema.e_commerce.ui.settings.auth.password

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.retrofit.RetrofitBuilder

class SignInPasswordViewModel(
    application: Application,
    val authenticationRepo: AuthRepo
) : AndroidViewModel(application) {


    class Factory(
        private val application: Application,
        private val authRepo: AuthRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInPasswordViewModel(application, authRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): SignInPasswordViewModel {
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
            )[SignInPasswordViewModel::class.java]
        }
    }
}