package com.example.presentation.mapper

import com.example.domain.model.UserDomainModel
import com.example.presentation.model.UserUiModel
import javax.inject.Inject

interface UserDomainToUiModelMapper {
    fun toUiModel(
        userDomainModelList: List<UserDomainModel>
    ): List<UserUiModel>
}

class UserDomainToUiModelMapperImpl @Inject constructor() : UserDomainToUiModelMapper {
    override fun toUiModel(
        userDomainModelList: List<UserDomainModel>
    ): List<UserUiModel> = userDomainModelList.map { domainModel ->
        UserUiModel(
            name = domainModel.name,
            username = domainModel.username,
            lat = domainModel.lat,
            lng = domainModel.lng
        )
    }
}
