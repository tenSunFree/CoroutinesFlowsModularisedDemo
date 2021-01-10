package com.example.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.domain.UserRepository
import com.example.domain.mapper.UserDomainFilter
import com.example.domain.model.UserDomainModel
import javax.inject.Inject

interface GetUser {
    suspend fun execute(): Flow<List<UserDomainModel>>
}

class GetUserImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userDomainFilter: UserDomainFilter
) : GetUser {

    override suspend fun execute(): Flow<List<UserDomainModel>> {
        return userRepository.getUser().map { domainModel ->
            userDomainFilter.filter(
                domainModel
            )
        }
    }

}
