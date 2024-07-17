package com.example.myapplication.sharedComponents

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.myapplication.features.registerScreen.domain.data.Constants

class MovieAppModule {
    companion object{
        private lateinit var sharedPreferences: SharedPreferences
        fun initDatabase(appContext: Context) {
            sharedPreferences = appContext.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)
        }

        val preferences: SharedPreferences get() = sharedPreferences
    }
}