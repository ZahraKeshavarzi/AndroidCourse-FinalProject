package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieMetadata(
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