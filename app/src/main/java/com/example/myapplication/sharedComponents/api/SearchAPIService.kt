package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchAPIService {
    @GET("movies/1/{search_keyword}")
    suspend fun getMoviesByKeyword(
        @Path("search_keyword") searchKeyword: String
    ): Response<MovieResponse>
}