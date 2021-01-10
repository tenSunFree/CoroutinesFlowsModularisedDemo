package com.example.coroutinesflowsmodulariseddemo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinesflowsmodulariseddemo.R
import com.example.presentation.MainViewModel
import com.example.presentation.model.UserUiModel
import com.mitteloupe.solid.recyclerview.SolidAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class UserFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private var adapter: SolidAdapter<UserViewHolder, UserUiModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dashboard, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setViewModelObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
    }

    private fun setupRecyclerView() {
        adapter = SolidAdapter(
            UserViewProvider(layoutInflater),
            { view, _ -> UserViewHolder(view) },
            UserViewBinder()
        )
        recycler_view.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager
    }

    private fun setViewModelObservers() {
        viewModel.launches.observe(viewLifecycleOwner, Observer { launches ->
            Log.d("more", "launches")
            bindLaunches(launches)
        })
        viewModel.loadingBody.observe(viewLifecycleOwner, Observer { show ->
            Log.d("more", "loadingBody")
            showLoadingBody(show)
        })
        viewModel.bodyError.observe(viewLifecycleOwner, Observer { show ->
            Log.d("more", "bodyError, show: $show")
            showBodyError()
        })
        // viewModel.bodyError.observeEvent(this) {
        //    Log.d("more", "vvvv")
        //     showBodyError()
        // }
    }

    private fun bindLaunches(launchesUiModel: List<UserUiModel>?) {
        launchesUiModel?.let { launches ->
            adapter?.setItems(launches)
            text_view_error.isVisible = false
        }
    }

    private fun showLoadingBody(loading: Boolean) {
        progress_bar.isVisible = loading
    }

    // private fun showLoadingHeader(loading: Boolean) {
    //     progressBarHeader.isVisible = loading
    // }

    private fun showBodyError() {
        text_view_error.isVisible = true
    }
}