package com.example.presentation.di

import com.example.presentation.mapper.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class PresentationModule {

    @Provides
    @Reusable
    fun provideLaunchesDomainToUiModelMapper(
    ): UserDomainToUiModelMapper = UserDomainToUiModelMapperImpl()
}