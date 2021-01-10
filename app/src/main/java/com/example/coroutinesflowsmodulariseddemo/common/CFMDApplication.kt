package com.example.coroutinesflowsmodulariseddemo.common

import com.example.coroutinesflowsmodulariseddemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class CFMDApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .build()
}
