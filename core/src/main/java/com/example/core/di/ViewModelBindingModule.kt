package com.example.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.example.core.android.InjectingViewModelFactory

@Module
abstract class ViewModelBindingModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}
