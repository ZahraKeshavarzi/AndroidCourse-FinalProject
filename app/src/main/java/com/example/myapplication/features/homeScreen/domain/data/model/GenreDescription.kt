package com.example.myapplication.features.homeScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreDescription(
    @SerializedName("en")
    val en: String,
    @SerializedName("fa")
    val fa: String
): Serializable
