package com.example.myapplication.features.homeScreen.domain.data.repository

import com.example.myapplication.features.homeScreen.domain.data.model.GenreResponse
import com.example.myapplication.sharedComponents.api.GenreAPIService

class GenreRepository(private val genreAPIService: GenreAPIService) {
    suspend fun getAllGenres(): Result<GenreResponse> {
        val response = genreAPIService.getAllGenre()
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("ERROR fetching genres!"))
        } else {
            Result.failure(Throwable("Service FAILED!"))
        }
    }
}