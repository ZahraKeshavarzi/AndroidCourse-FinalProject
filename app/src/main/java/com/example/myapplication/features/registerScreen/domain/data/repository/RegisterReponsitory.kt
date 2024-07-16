package com.example.myapplication.features.registerScreen.domain.data.repository

import com.example.myapplication.features.registerScreen.domain.data.model.RegisterResponse
import com.example.myapplication.features.registerScreen.domain.data.model.User
import com.example.myapplication.sharedComponents.api.RegisterAPIService

class RegisterRepository(private val registerAPIService: RegisterAPIService) {
    suspend fun registerUser(user: User): Result<RegisterResponse> {
        val response = registerAPIService.registerUser(user)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("an error occurred doing service"))
        } else {
            Result.failure(Throwable("service failed"))
        }
    }
}