package com.example.data_api.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import com.example.data_api.ApiService
import com.example.data_api.data.UserRemoteSourceImpl
import com.example.data_api.mapper.*
import com.example.data.UserRemoteSource
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
class ApiModule {

    @FlowPreview
    @Singleton
    @Provides
    fun provideSpaceXRemoteSource(
        apiService: ApiService,
        userRepositoryMapper: UserResponseToRepositoryModelMapper
    ): UserRemoteSource =
        UserRemoteSourceImpl(apiService, userRepositoryMapper)

    @Reusable
    @Provides
    fun provideLaunchesResponseToRepositoryModelMapper()
            : UserResponseToRepositoryModelMapper = UserResponseToRepositoryModelMapperImpl()

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        internal fun provideApi(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideRetrofit(
            httpBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder
        ): Retrofit = retrofitBuilder
            .client(httpBuilder.build())
            .build()
    }
}