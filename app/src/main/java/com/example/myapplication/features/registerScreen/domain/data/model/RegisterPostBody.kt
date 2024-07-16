package com.example.myapplication.features.registerScreen.domain.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterPostBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("studentNumber")
    val studentNumber: String,
    @SerializedName("password")
    val password: String
): Serializable
