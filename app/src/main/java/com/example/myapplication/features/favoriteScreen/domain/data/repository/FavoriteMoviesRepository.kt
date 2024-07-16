//package com.example.myapplication.features.favoriteScreen.domain.data.repository
//
//import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsResponse
//import com.example.myapplication.features.homeScreen.domain.data.model.MovieData
//
//suspend fun getFavoriteMovies(): Result<MovieData> {
//    val response = api.getMovieDetailsByID(movieId)
//    return if (response.isSuccessful) {
//        response.body()?.let {
//            Result.success(it)
//        } ?: Result.failure(Throwable("ERROR fetching movies!"))
//    } else {
//        Result.failure(Throwable("Service FAILED!"))
//    }
//}