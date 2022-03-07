package com.example.gocart.auth.sharedpreferences

import android.app.Application
import android.preference.PreferenceManager
import android.provider.Settings
import android.provider.Settings.Global.putString
import androidx.lifecycle.MutableLiveData
import com.example.gocart.utils.Constants.ALL_DATA_ROUTE
import com.google.gson.Gson
import java.util.*

//class SettingsPreferences(private val application: Application): ISettingsPreferences {
////    companion object{
////        private var instance:SettingsPreferences?=null
////        private val lock = Any()
////
////        operator fun invoke(application: Application)= instance?: synchronized(lock){
////             instance ?: SettingsPreferences(application)
////        }
////    }
//////    val Settings:MutableLiveData<Settings> by lazy {
//////        MutableLiveData<Settings>().apply {
//////            postValue(Settings.getDefault())
//////        }
//////    }
//////
//////    private val sp:SettingsPreferences by lazy {
//////        PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
//////    }
////
////    private fun settingsToJson(settings: Settings):String{
////        val json = Gson()
////        return json.toJson(settings)
////    }
////    private fun settingsFromJson(settings:String): Settings{
////        val json = Gson()
////        return json.fromJson(settings, Settings::class.java)
////    }
////
////    override fun insert(settings: Settings) {
////        sp.edit{
////            putString(ALL_DATA_ROUTE,settingsToJson(settings))
////            apply()
////        }
////        getSettingsLiveData()
////    }
////
////    override fun update(update: (Settings) -> Settings) {
////        sp.edit{
////            putString(ALL_DATA_ROUTE,settingsToJson(update(settings.value?:Settings.getDefault())))
////            apply()
////        }
////        getSettingsLiveData()
////    }
////
////    override fun getSettingsLiveData(): MutableLiveData<Settings> {
////        sp.getString(ALL_DATA_ROUTE,null)?.let{ Settings.postValue(settingsFromJson(it))}
////        return Settings
////    }
////
////    override fun getSettings(): Settings {
////        return sp.getString(ALL_DATA_ROUTE,null)?.let{ settingsFromJson(it)?: Settings.getDefault}
////    }
//}
//
