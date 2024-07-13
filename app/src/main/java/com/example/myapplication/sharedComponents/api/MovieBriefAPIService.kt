package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieBriefAPIService {
    @GET("genres/{genre_id}/movies")
    suspend fun getMoviesByGenre(
        @Path("genre_id") genreId: Int
    ): MovieResponse
}

