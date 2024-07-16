package com.example.myapplication.features.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySplashscreenBinding
import com.example.myapplication.features.homeScreen.HomeScreenActivity
import com.example.myapplication.features.registerScreen.RegisterScreenActivity
import com.example.myapplication.features.registerScreen.domain.data.Constants


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    //region properties
    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    //endregion

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialBinding()
        initialSharedPreference()
    }
    //endregion


    //region methods
    @SuppressLint("SetTextI18n")
    private fun initialBinding() {
        binding.applicationLogo.setImageResource(R.drawable.application_logo)
        binding.applicationName.text = "Top Movies App"
    }

    private fun initialSharedPreference() {
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)
        val token = sharedPreferences.getString(Constants.USER_TOKEN_KEY, "").toString()
        if (token.isEmpty()) {
            val intent = Intent(this, RegisterScreenActivity::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }

    }

    //endregion


}