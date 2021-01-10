package com.example.data

import kotlinx.coroutines.flow.Flow
import com.example.data.model.UserRepositoryModel

interface UserRemoteSource {
    suspend fun getAllLaunches(): Flow<List<UserRepositoryModel>>
}