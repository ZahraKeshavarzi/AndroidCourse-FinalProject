package com.example.myapplication.features.registerScreen.domain.data.model

data class RegisterPostBody(
    val email: String,
    val name: String,
    val studentNumber: String,
    val password: String
)
