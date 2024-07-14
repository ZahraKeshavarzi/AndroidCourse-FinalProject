package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.homeScreen.domain.data.model.GenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenreAPIService {
    @GET("genres")
    suspend fun getAllGenre(): Response<GenreResponse>
}