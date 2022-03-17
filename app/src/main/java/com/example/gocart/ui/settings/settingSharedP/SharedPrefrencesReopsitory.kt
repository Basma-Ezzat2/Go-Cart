package com.example.gocart.ui.settings.settingSharedP

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng

class SharedPrefrencesReopsitory(context: Application) : AndroidViewModel(context) {
    private val setting : SettingSB = SettingSB(context)
    fun updateSetting(settingModel: SettingsModel)=setting.saveSetting(settingModel)
    fun getSetting(): LiveData<SettingsModel>{
        setting.loadSetting()
        return setting.getSetting()
    }

}