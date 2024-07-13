package com.example.myapplication.features.homeScreen.domain.data.repository

import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.sharedComponents.api.MovieBriefAPIService

class MovieRepository(private val movieBriefApiService: MovieBriefAPIService) {

    suspend fun getMoviesByGenre(genreId: Int): List<MovieResponse.MovieItem> {
        val response = movieBriefApiService.getMoviesByGenre(genreId)
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
