package com.example.myapplication.features.homeScreen.domain.data.repository

import MovieResponse
import com.example.myapplication.sharedComponents.api.APIService

class MovieRepository(private val apiService: APIService) {

    suspend fun getMoviesByGenre(genreId: Int): List<MovieResponse.MovieItem> {
        val response = apiService.getMoviesByGenre(genreId)
        return response.data // Return just the list of movies
    }
}


//class MovieRepository(private val api: APIService) {
//
//    suspend fun getMoviesByGenre(genreId: Int): Result<MovieResponse> {
//        val response = api.getMoviesByGenre(genreId)
//        return if (response.isSuccessful) {
//            val body = response.body()
//            if (body != null) {
//                Result.success(body)
//            } else {
//                Result.failure(Throwable("Error fetching movies"))
//            }
//        } else {
//            Result.failure(Throwable("Service failed"))
//        }
//    }
//}
