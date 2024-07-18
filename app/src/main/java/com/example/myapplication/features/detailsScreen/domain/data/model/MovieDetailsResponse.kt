package com.example.myapplication.features.detailsScreen.domain.data.model

import com.example.myapplication.features.registerScreen.domain.data.model.Description

data class MovieDetailsResponse(
    val data: MovieDetailsData,
    val description: Description,
    val status: Int
)
