package com.example.gocart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.R
import com.example.gocart.ui.onBoarding.OnBoarding
import com.example.gocart.ui.settings.SettingsViewModel
import com.example.gocart.utils.Constants.isUSD

class SplashScreen : AppCompatActivity() {

    lateinit var stViewModel : SettingsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        stViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        getSetting()

        val splashTimeOut = 2500
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
            val signInIntent = Intent(this, OnBoarding::class.java)
            startActivity(signInIntent)
            finish()
        }, splashTimeOut.toLong())
    }

    fun getSetting() {
        stViewModel.getSetting().observe(this, {
            val currency: String = it.currency
            isUSD = currency=="usd"



        })
    }
}