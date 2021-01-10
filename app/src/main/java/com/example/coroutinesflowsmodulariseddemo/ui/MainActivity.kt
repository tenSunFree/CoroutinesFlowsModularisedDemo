package com.example.coroutinesflowsmodulariseddemo.ui

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.coroutinesflowsmodulariseddemo.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = factory
        setContentView(R.layout.activity_main)
    }
}
