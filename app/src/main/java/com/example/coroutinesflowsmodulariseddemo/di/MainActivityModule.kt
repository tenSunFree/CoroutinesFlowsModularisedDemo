package com.example.coroutinesflowsmodulariseddemo.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.FragmentKey
import com.example.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import com.example.coroutinesflowsmodulariseddemo.ui.UserFragment
import com.example.coroutinesflowsmodulariseddemo.ui.MainActivity
import com.example.presentation.MainViewModel
import com.example.presentation.MainViewModelImpl

@Module
internal abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @Binds
    @IntoMap
    @FragmentKey(UserFragment::class)
    abstract fun provideUserFragment(userFragment: UserFragment): Fragment

    @FlowPreview
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @ExperimentalCoroutinesApi
    abstract fun provideMainViewModelImpl(viewModel: MainViewModelImpl): ViewModel
}
