package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.registerScreen.domain.data.model.RegisterPostBody
import com.example.myapplication.features.registerScreen.domain.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAPIService {

    @POST("register")
    suspend fun registerUser(@Body body: RegisterPostBody): Response<RegisterResponse>

}