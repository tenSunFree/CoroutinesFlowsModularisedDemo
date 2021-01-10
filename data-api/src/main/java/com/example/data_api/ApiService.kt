package com.example.data_api

import com.example.data_api.model.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getAllLaunches(): List<UserResponse>
}
