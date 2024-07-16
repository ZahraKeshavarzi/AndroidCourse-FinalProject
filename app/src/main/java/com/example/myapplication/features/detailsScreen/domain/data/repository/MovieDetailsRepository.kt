package com.example.myapplication.features.detailsScreen.domain.data.repository

import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsResponse
import com.example.myapplication.sharedComponents.api.MovieDetailsAPIService

class MovieDetailsRepository (private val api: MovieDetailsAPIService) {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResponse> {
        val response = api.getMovieDetailsByID(movieId)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("ERROR fetching movies!"))
        } else {
            Result.failure(Throwable("Service FAILED!"))
        }
    }
}
