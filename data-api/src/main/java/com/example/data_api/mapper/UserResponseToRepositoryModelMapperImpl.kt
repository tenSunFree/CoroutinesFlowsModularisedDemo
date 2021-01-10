package com.example.data_api.mapper

import com.example.data.model.UserRepositoryModel
import dagger.Reusable
import com.example.data_api.model.UserResponse
import javax.inject.Inject

interface UserResponseToRepositoryModelMapper {
    fun toRepositoryModel(launchesResponse: List<UserResponse>): List<UserRepositoryModel>
}

@Reusable
class UserResponseToRepositoryModelMapperImpl @Inject constructor() :
    UserResponseToRepositoryModelMapper {
    override fun toRepositoryModel(
        launchesResponse: List<UserResponse>
    ): List<UserRepositoryModel> {
        return launchesResponse.map { response ->

            UserRepositoryModel(
                name = response.name.orEmpty(),
                username = response.username.orEmpty(),
                lat = response.address?.geo?.lat.orEmpty(),
                lng = response.address?.geo?.lng.orEmpty()
            )
        }
    }
}
