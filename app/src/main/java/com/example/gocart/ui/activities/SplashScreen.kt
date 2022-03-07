package com.example.gocart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gocart.R
import com.example.gocart.ui.onBoarding.OnBoarding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
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
}