package com.example.gocart.ui.settings.settingSharedP

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingSB(val context: Context) {

    companion object {
        const val fileName = "Current"
    }
    private val SettingData: MutableLiveData<SettingsModel> = MutableLiveData<SettingsModel>()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE)


    fun saveSetting(settingModel: SettingsModel){
        CoroutineScope(Dispatchers.IO).launch {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("lang",settingModel.lang)
            editor.putString("currency",settingModel.currency)
            editor.apply()
            editor.commit()
        }
    }

    fun loadSetting(){
        CoroutineScope(Dispatchers.IO).launch {
            val lang = sharedPreferences.getString("lang","en")
            val currency = sharedPreferences.getString("currency","usd")
            SettingData.postValue(SettingsModel( lang!!, currency!!))
        }
    }



    fun getSetting(): LiveData<SettingsModel>{
        return SettingData
    }


}