package com.example.data.di

import com.example.data.UserRemoteSource
import com.example.data.mapper.UserRepositoryToDomainModelMapper
import com.example.data.mapper.UserRepositoryToDomainModelMapperImpl
import com.example.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import com.example.domain.UserRepository

@Module
class UserRepositoryModule {

    @Provides
    @Reusable
    fun provideUserRepository(
        userRemoteSource: UserRemoteSource,
        userRepositoryToDomainModelMapper: UserRepositoryToDomainModelMapper
    ): UserRepository {
        return UserRepositoryImpl(
            userRemoteSource,
            userRepositoryToDomainModelMapper
        )
    }

    @Provides
    @Reusable
    fun provideUserRepositoryToDomainModelMapper(): UserRepositoryToDomainModelMapper =
        UserRepositoryToDomainModelMapperImpl()
}
