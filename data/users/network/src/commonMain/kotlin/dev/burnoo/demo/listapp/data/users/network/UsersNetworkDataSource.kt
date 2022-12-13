package dev.burnoo.demo.listapp.data.users.network

import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUsersResponse

interface UsersNetworkDataSource {

    suspend fun getUsers(page: Int): Result<NetworkUsersResponse, NetworkError>

    suspend fun getUser(userId: String): Result<NetworkUser, NetworkError>

    companion object {
        const val BASE_PATH = "/data/v1"
    }
}
