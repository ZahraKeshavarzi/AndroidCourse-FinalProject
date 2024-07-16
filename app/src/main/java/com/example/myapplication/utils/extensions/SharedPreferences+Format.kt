package com.example.myapplication.utils.extensions

import android.content.SharedPreferences

fun SharedPreferences.putString(key: String, value: String) {
    this.edit().putString(key, value).apply()
}