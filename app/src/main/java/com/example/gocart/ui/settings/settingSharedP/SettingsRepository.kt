package com.example.gocart.ui.settings.settingSharedP

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.*

class SettingsRepository (application: Application) : AndroidViewModel(application){

    private val sharedPreferencesReopsitory: SharedPrefrencesReopsitory =
        SharedPrefrencesReopsitory(application)

    @Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun getSetting(): LiveData<SettingsModel> {
        return sharedPreferencesReopsitory.getSetting()
    }

    fun setSetting(settingsModel: SettingsModel) {
        sharedPreferencesReopsitory.updateSetting(settingsModel)
    }
}