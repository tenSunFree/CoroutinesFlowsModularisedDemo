package com.example.core.di

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import com.example.core.android.InjectingFragmentFactory

@Module
abstract class FragmentBindingModule {

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}
