package com.example.data.mapper

import com.example.data.model.UserRepositoryModel
import com.example.domain.model.UserDomainModel
import javax.inject.Inject

interface UserRepositoryToDomainModelMapper {
    fun toDomainModel(launchesRepositoryModel: List<UserRepositoryModel>): List<UserDomainModel>
}

class UserRepositoryToDomainModelMapperImpl @Inject constructor() :
    UserRepositoryToDomainModelMapper {
    override fun toDomainModel(
        launchesRepositoryModel: List<UserRepositoryModel>
    ): List<UserDomainModel> =
        launchesRepositoryModel.map { launchRepositoryModel ->
            UserDomainModel(
                name = launchRepositoryModel.name,
                username = launchRepositoryModel.username,
                lat = launchRepositoryModel.lat,
                lng = launchRepositoryModel.lng
            )
        }
}
