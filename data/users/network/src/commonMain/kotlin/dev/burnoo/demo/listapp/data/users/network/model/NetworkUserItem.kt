package dev.burnoo.demo.listapp.data.users.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUserItem(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
)
