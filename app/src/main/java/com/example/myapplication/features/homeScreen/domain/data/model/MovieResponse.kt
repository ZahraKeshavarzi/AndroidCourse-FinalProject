package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("description")
    val description: Map<String, String>,
    @SerializedName("data")
    val data: List<MovieItem>,
    @SerializedName("metadata")
    val metadata: Metadata
) : Serializable {

    data class MovieItem(
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
        val images: List<String>,
        @SerializedName("runtime")
        val runtime: String,
        @SerializedName("released")
        val released: String,
        @SerializedName("plot")
        val plot: String,
        @SerializedName("actors")
        val actors: String
    ) : Serializable

    data class Metadata(
        @SerializedName("has_next")
        val hasNext: Boolean,
        @SerializedName("has_prev")
        val hasPrev: Boolean,
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("page_count")
        val pageCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    ) : Serializable
}
