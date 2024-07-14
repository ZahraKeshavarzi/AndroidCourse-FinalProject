package com.example.myapplication.features.detailsScreen.domain.data.model

data class MovieDetailsResponse(
    val data: MovieDetailsData,
    val description: Map<String, String>,
    val status: Int
)
