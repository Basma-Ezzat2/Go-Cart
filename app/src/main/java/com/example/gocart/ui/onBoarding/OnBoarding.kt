package com.example.gocart.ui.onBoarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.gocart.R
import com.example.gocart.ui.activities.MainActivity

import com.google.android.material.tabs.TabLayout

class OnBoarding : AppCompatActivity() {
    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check if app already running once time
        if (restorePrefData()) {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next)

        // add data to model class
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(
            OnBoardingData(
                "Discount up to 73%",
                "Be the first to know of upcoming sales\n and special for the brands you love.",
                R.drawable.ob1
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Choose and checkout",
                "Explore the variety of products available and\n pay less with the amazing offers applicable.",
                R.drawable.ob02
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Secure Payment",
                "Collect your favourites and\n    pay online seamlessly.",
                R.drawable.ob3
            )
        )
        setOnBoardingViewPagerAdapter(onBoardingData)
        position = onBoardingViewPager!!.currentItem

        //
        next?.setOnClickListener {
            if (position < onBoardingData.size) {
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size) {
                savePrefData()
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == onBoardingData.size - 1) {
                    next!!.text = "Get Started"
                } else {
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter

        // set tab layout
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }

    // add shared pref and store boolean (check if u are first time running app or not)
    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }
    private fun restorePrefData(): Boolean {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)

        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}