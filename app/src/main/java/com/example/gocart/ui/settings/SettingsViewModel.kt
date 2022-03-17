package com.example.gocart.ui.settings

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.auth.repositories.AuthRepo
import com.example.gocart.auth.sharedpreferences.SharedPreferencesProvider
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.checkout.ConfirmPaymentViewModel
import com.example.gocart.ui.settings.settingSharedP.SettingsModel
import com.example.gocart.ui.settings.settingSharedP.SettingsRepository

class SettingsViewModel (application: Application) : AndroidViewModel(application) {
    private val mApplication: Application = application
    val settingsRepository: SettingsRepository = SettingsRepository(mApplication)


    fun setLocale(activity: Activity, languageCode: String?) {
        settingsRepository.setLocale(activity, languageCode)
    }

    fun getSetting(): LiveData<SettingsModel> {
        return settingsRepository.getSetting()
    }

    fun setSetting(settingModel: SettingsModel) {
        settingsRepository.setSetting(settingModel)
    }

    class Factory(private val application: Application, val AuthRepo: AuthRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingsViewModel(application) as T
        }
    }

    companion object {
        fun create(context: Fragment): SettingsViewModel {
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
            )[SettingsViewModel::class.java]
        }
    }
}