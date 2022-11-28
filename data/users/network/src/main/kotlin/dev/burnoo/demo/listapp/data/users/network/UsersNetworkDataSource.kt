package dev.burnoo.demo.listapp.data.users.network

import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem

interface UsersNetworkDataSource {

    suspend fun getUsers(): Result<List<NetworkUserItem>, NetworkError>

    suspend fun getUser(userId: String): NetworkUser
}
