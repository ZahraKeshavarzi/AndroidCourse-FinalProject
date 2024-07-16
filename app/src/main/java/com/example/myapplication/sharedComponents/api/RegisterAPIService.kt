package com.example.myapplication.sharedComponents.api

import com.example.myapplication.features.registerScreen.domain.data.model.RegisterResponse
import com.example.myapplication.features.registerScreen.domain.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAPIService {

    @POST("register")
    suspend fun registerUser(
        @Body user: User
    ): Response<RegisterResponse>
}