package com.example.myapplication.features.homeScreen.domain.data.repository

import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.sharedComponents.api.MovieBriefAPIService

class MovieRepository(private val movieBriefApiService: MovieBriefAPIService) {

    suspend fun getAllMovies(genreId: Int): Result<MovieResponse> {
        val response = movieBriefApiService.getMoviesByGenre(genreId)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("ERROR fetching movies!"))
        } else {
            Result.failure(Throwable("Service FAILED!"))
        }
    }
}
