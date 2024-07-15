package com.example.myapplication.features.searchScreen.domain.data.repository

import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.sharedComponents.api.SearchAPIService

class MovieSearchRepository(private val searchAPIService: SearchAPIService) {

    suspend fun getAllMoviesByKeyword(searchKeyword: String): Result<MovieResponse> {
        val response = searchAPIService.getMoviesByKeyword(searchKeyword)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("ERROR fetching movies!"))
        } else {
            Result.failure(Throwable("Service FAILED!"))
        }
    }
}
