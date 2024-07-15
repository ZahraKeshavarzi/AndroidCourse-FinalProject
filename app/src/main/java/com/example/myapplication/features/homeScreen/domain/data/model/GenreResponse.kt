package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("description")
    val description: GenreDescription,
    @SerializedName("data")
    val data: List<GenreData>
) : Serializable

