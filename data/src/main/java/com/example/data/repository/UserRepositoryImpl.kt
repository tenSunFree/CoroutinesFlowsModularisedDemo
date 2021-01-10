package com.example.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.data.UserRemoteSource
import com.example.data.mapper.UserRepositoryToDomainModelMapper
import com.example.domain.model.UserDomainModel
import com.example.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val userDomainMapper: UserRepositoryToDomainModelMapper
) : UserRepository {

    override suspend fun getUser(): Flow<List<UserDomainModel>> {
        return userRemoteSource.getAllLaunches().map { repositoryModel ->
            userDomainMapper.toDomainModel(repositoryModel)
        }
    }
}
