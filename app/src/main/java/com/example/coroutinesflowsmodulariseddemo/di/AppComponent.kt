package com.example.coroutinesflowsmodulariseddemo.di

import com.example.core.di.FragmentBindingModule
import com.example.core.di.ViewModelBindingModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.example.coroutinesflowsmodulariseddemo.common.CFMDApplication
import com.example.data.di.UserRepositoryModule
import com.example.data_api.di.ApiModule
import com.example.data_api.di.NetworkModule
import com.example.domain.di.DomainModule
import com.example.presentation.di.PresentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        MainActivityModule::class,
        PresentationModule::class,
        NetworkModule::class,
        DomainModule::class,
        UserRepositoryModule::class,
        ViewModelBindingModule::class,
        FragmentBindingModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<CFMDApplication>
