package com.example.data_api.data

import com.example.data.model.UserRepositoryModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import com.example.data.UserRemoteSource
import com.example.data_api.ApiService
import com.example.data_api.mapper.UserResponseToRepositoryModelMapper
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class UserRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val userRepositoryMapper: UserResponseToRepositoryModelMapper
) : UserRemoteSource {

    private val launchesChannel = ConflatedBroadcastChannel<List<UserRepositoryModel>>()

    override suspend fun getAllLaunches(): Flow<List<UserRepositoryModel>> {
        val allLaunchesResponse = apiService.getAllLaunches()
        val launchesRepositoryModel =
            userRepositoryMapper.toRepositoryModel(allLaunchesResponse)
        launchesChannel.offer(launchesRepositoryModel)
        return launchesChannel.asFlow()
    }
}
