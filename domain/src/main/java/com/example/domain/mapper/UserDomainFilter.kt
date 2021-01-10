package com.example.domain.mapper

import com.example.domain.model.UserDomainModel
import javax.inject.Inject

interface UserDomainFilter {
    fun filter(
        userDomainModelList: List<UserDomainModel>
    ): List<UserDomainModel>
}

class UserDomainFilterImpl @Inject constructor() : UserDomainFilter {
    override fun filter(
        userDomainModelList: List<UserDomainModel>
    ): List<UserDomainModel> = userDomainModelList.filter { domainModel ->
        domainModel.name.contains("")
    }
}
