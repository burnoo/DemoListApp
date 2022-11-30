package dev.burnoo.demo.listapp.data.users.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUsersResponse(
    val data: List<NetworkUserItem>,
    val total: Int,
    val page: Int,
    val limit: Int,
)
