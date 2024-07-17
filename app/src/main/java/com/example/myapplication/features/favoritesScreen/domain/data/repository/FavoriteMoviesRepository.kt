package com.example.myapplication.features.favoritesScreen.domain.data.repository

import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsData
import com.example.myapplication.sharedComponents.MovieAppModule

class FavoriteMoviesRepository {

    fun getAllFavoriteMovies(): List<MovieDetailsData> {
        return MovieAppModule.getMovieList()
    }
}

