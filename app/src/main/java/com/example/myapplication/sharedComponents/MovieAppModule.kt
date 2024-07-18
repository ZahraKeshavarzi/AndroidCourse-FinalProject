package com.example.myapplication.sharedComponents

//import com.example.myapplication.sharedComponents.db.FavoriteMoviesDatabase
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsData
import com.example.myapplication.features.registerScreen.domain.data.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieAppModule {
    companion object {

        private lateinit var sharedPreferences: SharedPreferences
        private val gson = Gson()
        private const val MOVIE_LIST_KEY = "movie_list"

        fun initSharedPreferences(appContext: Context) {
            sharedPreferences =
                appContext.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        }

        val preferences: SharedPreferences
            get() = sharedPreferences

        fun saveMovieList(movieList: List<MovieDetailsData>) {
            val json = gson.toJson(movieList)
            sharedPreferences.edit().putString(MOVIE_LIST_KEY, json).apply()
        }

        fun getMovieList(): List<MovieDetailsData> {
            val json = sharedPreferences.getString(MOVIE_LIST_KEY, null)
            if (json.isNullOrEmpty()) {
                return emptyList()
            }
            val type = object : TypeToken<List<MovieDetailsData>>() {}.type
            return gson.fromJson(json, type)
        }

        fun toggleMovieInList(movie: MovieDetailsData) {
            val currentList = getMovieList().toMutableList()
            if (currentList.any { it.id == movie.id }) {
                currentList.removeAll { it.id == movie.id }
            } else {
                currentList.add(movie)
            }
            saveMovieList(currentList)
        }


    }
}
