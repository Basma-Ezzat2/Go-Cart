package com.example.gocart.auth.sharedpreferences

import android.provider.Settings
import androidx.lifecycle.MutableLiveData

interface ISettingsPreferences {
    fun insert(settings: Settings)
    fun update(update:(Settings)-> Settings)
    fun getSettingsLiveData(): MutableLiveData<Settings>
    fun getSettings(): Settings
}