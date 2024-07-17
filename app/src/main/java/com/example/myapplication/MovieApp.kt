package com.example.myapplication

import android.app.Application
import com.example.myapplication.sharedComponents.MovieAppModule

class MovieApp : Application() {

    //region lifecycle
    override fun onCreate() {
        super.onCreate()
        MovieAppModule.initDatabase(this)
    }
    //endregion
}