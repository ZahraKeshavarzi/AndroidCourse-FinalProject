package com.example.myapplication.sharedComponents

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.myapplication.features.registerScreen.domain.data.Constants
//import com.example.myapplication.sharedComponents.db.FavoriteMoviesDatabase

class MovieAppModule {
    companion object {

        private lateinit var sharedPreferences: SharedPreferences
        fun initSharedPreferences(appContext: Context) {
            sharedPreferences =
                appContext.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)
        }

        val preferences: SharedPreferences get() = sharedPreferences


//        private lateinit var favoriteMoviesDatabase: FavoriteMoviesDatabase
//        fun initDatabase(appContext: Context) {
//            favoriteMoviesDatabase = Room.databaseBuilder(
//                appContext,
//                FavoriteMoviesDatabase::class.java,
//                com.example.myapplication.sharedComponents.db.Constants.FAVORITE_MOVIE_DATABASE
//            )
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//        val database: FavoriteMoviesDatabase get() = favoriteMoviesDatabase
    }
}