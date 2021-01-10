package com.example.domain.di

import com.example.domain.mapper.UserDomainFilter
import com.example.domain.mapper.UserDomainFilterImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import com.example.domain.UserRepository
import com.example.domain.usecase.GetUser
import com.example.domain.usecase.GetUserImpl

@Module
class DomainModule {

    @Provides
    @Reusable
    fun provideGetUser(
        userRepository: UserRepository,
        filter: UserDomainFilter
    ): GetUser = GetUserImpl(userRepository, filter)

    @Provides
    @Reusable
    fun provideUserDomainFilter(): UserDomainFilter =
            UserDomainFilterImpl()
}
