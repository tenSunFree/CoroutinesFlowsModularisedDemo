package com.example.domain

import kotlinx.coroutines.flow.Flow
import com.example.domain.model.UserDomainModel

interface UserRepository {
    suspend fun getUser(): Flow<List<UserDomainModel>>
}