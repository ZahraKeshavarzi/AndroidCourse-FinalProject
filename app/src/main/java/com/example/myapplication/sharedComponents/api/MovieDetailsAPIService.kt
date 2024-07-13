package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsAPIService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsByID(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailsResponse>
}