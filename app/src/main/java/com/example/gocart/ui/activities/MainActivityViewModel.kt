package com.example.gocart.ui.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gocart.ui.settings.settingSharedP.SettingsModel
import com.example.gocart.ui.settings.settingSharedP.SettingsRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mApplication: Application = application
    val settingsRepository: SettingsRepository = SettingsRepository(mApplication)


    fun getSetting(): LiveData<SettingsModel> {
        return settingsRepository.getSetting()
    }
}