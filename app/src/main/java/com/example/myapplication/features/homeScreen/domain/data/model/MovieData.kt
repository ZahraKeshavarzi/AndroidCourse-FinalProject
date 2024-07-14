package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("images")
    val images: List<String>
) : Serializable
