package com.example.myapplication.features.registerScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("description")
    val description: String
): Serializable
