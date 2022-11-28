package dev.burnoo.demo.listapp.data.users.network.api

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val BASE_URL = "https://dummyapi.io/data/v1"
private const val APP_ID = "637797e82364777fa9814112"

internal class UsersApi(engine: HttpClientEngine) : UsersNetworkDataSource {

    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        defaultRequest {
            header("app-id", APP_ID)
        }
    }

    override suspend fun getUsers(): Result<List<NetworkUserItem>, NetworkError> {
        return runCatching {
            client.get("$BASE_URL/user")
                .body<NetworkUsersResponse>()
                .data
        }.mapNetworkError()
    }

    override suspend fun getUser(userId: String): NetworkUser {
        return client.get("$BASE_URL/user/$userId").body()
    }

    private fun <V, E : Throwable> Result<V, E>.mapNetworkError(): Result<V, NetworkError> {
        return mapError {
            when (it) {
                is ClientRequestException -> NetworkError.Client
                is ServerResponseException -> NetworkError.Server
                else -> NetworkError.Device
            }
        }
    }
}
