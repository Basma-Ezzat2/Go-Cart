package com.example.gocart.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gocart.R
import com.example.gocart.databinding.ActivityMainBinding
import com.example.gocart.ui.home.viewmodels.HomeViewModel
import com.example.gocart.ui.network.Connectivity
import com.example.gocart.ui.settings.SettingsViewModel
import com.example.gocart.ui.settings.settingSharedP.SettingsModel
import com.example.gocart.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var connectivity : Connectivity
    private lateinit var mainViewModel : MainActivityViewModel
    private lateinit var setting: SettingsModel
    private lateinit var layout1 : ConstraintLayout
    private lateinit var layout2 : ConstraintLayout
    private lateinit var viewModel: SettingsViewModel


    val setingvm by lazy {
         ViewModelProvider(this).get(SettingsViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
        setingvm.getSetting().observe(this, {
            val lang: String = it.lang
            if (resources.configuration.locale.language!= Locale(lang).language){
                setingvm.setLocale(this,lang)
                // ActivityCompat.recreate(activity)
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }




        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        homeViewModel=ViewModelProvider(this)[HomeViewModel::class.java]
        mainViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        //homeViewModel.getData()
        checkNetworkConnection()
        layout1 = findViewById(R.id.layout1)
        layout2 = findViewById(R.id.layout2)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


           }
    private fun checkNetworkConnection() {
        connectivity = Connectivity(application)

        connectivity.observe(this, { isConnected ->

            if (isConnected){
                layout1.visibility = View.VISIBLE
                layout2.visibility = View.GONE

            }else{
                layout1.visibility = View.GONE
                layout2.visibility = View.VISIBLE
            }

        })
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("HOSSAM","Main activity messageee")

        liveData.postValue(ActivityResultDataClass(requestCode,resultCode,data))

    }

    var liveData = MutableLiveData<ActivityResultDataClass?>()

    data class ActivityResultDataClass (val requestCode: Int,val resultCode: Int,val data: Intent?)
}