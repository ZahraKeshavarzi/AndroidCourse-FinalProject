package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("description")
    val description: MovieDescription,
    @SerializedName("data")
    val data: List<MovieData>,
    @SerializedName("metadata")
    val metadata: MovieMetadata
) : Serializable
