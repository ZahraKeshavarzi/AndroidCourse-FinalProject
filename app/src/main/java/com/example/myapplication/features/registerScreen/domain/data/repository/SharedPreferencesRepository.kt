package com.example.myapplication.features.registerScreen.domain.data.repository

import android.content.SharedPreferences
import com.example.myapplication.utils.extensions.putString

class SharedPreferencesRepository(private val sharedPreferences: SharedPreferences) {

    fun getString(key: String) = sharedPreferences.getString(key, "").toString()

    fun putString(key: String, value: String) = sharedPreferences.putString(key, value)
}