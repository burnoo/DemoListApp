package dev.burnoo.demo.listapp.data.users.network

import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem

interface UsersNetworkDataSource {

    suspend fun getUsers(): List<NetworkUserItem>

    suspend fun getUser(userId: String): NetworkUser
}
