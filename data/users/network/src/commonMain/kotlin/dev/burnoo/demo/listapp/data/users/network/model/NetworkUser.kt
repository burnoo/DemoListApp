package dev.burnoo.demo.listapp.data.users.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String,
    val email: String,
    val phone: String,
)
