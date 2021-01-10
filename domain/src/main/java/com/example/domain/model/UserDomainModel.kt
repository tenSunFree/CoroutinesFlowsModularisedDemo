package com.example.domain.model

data class UserDomainModel(
    val name: String,
    val username: String,
    val lat: String,
    val lng: String,
    val isRedColor: Boolean = true
)
