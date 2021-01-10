package com.example.coroutinesflowsmodulariseddemo.ui

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesflowsmodulariseddemo.R
import com.example.presentation.model.UserUiModel
import com.mitteloupe.solid.recyclerview.InflatedViewProvider
import com.mitteloupe.solid.recyclerview.SimpleViewBinder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewProvider(
    layoutInflater: LayoutInflater
) : InflatedViewProvider(layoutInflater, R.layout.item_user)

class UserViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val nameTextView: TextView = containerView.text_view_name
    val usernameTextView: TextView = containerView.text_view_username
    val latTextView: TextView = containerView.text_view_lat
    val lngTextView: TextView = containerView.text_view_lng
}

class UserViewBinder : SimpleViewBinder<UserViewHolder, UserUiModel>() {

    override fun bindView(viewHolder: UserViewHolder, data: UserUiModel) {
        viewHolder.nameTextView.text = data.name
        viewHolder.usernameTextView.text = data.username
        viewHolder.latTextView.text = data.lat
        viewHolder.lngTextView.text = data.lng
        val randoms = (1..10).random()
        if (randoms > 5) {
            viewHolder.latTextView.setTextColor(true)
            viewHolder.lngTextView.setTextColor(true)
        } else {
            viewHolder.latTextView.setTextColor(false)
            viewHolder.lngTextView.setTextColor(false)
        }
    }

    private fun TextView.setTextColor(isRed: Boolean) {
        if (isRed) {
            setTextColor(
                ContextCompat.getColor(
                    context, android.R.color.holo_red_dark
                )
            )
        } else {
            setTextColor(
                ContextCompat.getColor(
                    context, android.R.color.holo_green_dark
                )
            )
        }
    }
}