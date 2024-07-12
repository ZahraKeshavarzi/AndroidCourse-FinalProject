package com.example.myapplication.sharedComponents.api

import MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

//interface APIService {
//    @GET("genres/{genre_id}/movies")
//    suspend fun getMoviesByGenre(
//        @Path("genre_id") genreId: Int
//    ): Response<MovieResponse>
//}
interface APIService {
    @GET("genres/{genre_id}/movies")
    suspend fun getMoviesByGenre(
        @Path("genre_id") genreId: Int
    ): MovieResponse  // Change Response<MovieResponse> to MovieResponse
}
