package dev.burnoo.demo.listapp.data.users.core.utils

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUsersResponse

val testNetworkUsers = List(5) {
    NetworkUserItem(
        id = it.toString(),
        title = "mrs",
        firstName = "John $it",
        lastName = "Doe",
        picture = "https://example.org/image$it.jpg",
    )
}

val testNetworkResponse = NetworkUsersResponse(
    data = testNetworkUsers,
    total = 122,
    page = 0,
    limit = 20,
)

val testNetworkUser = NetworkUser(
    title = "mrs",
    firstName = "John 0",
    lastName = "Doe",
    picture = "https://example.org/image0.jpg",
    gender = "male",
    email = "test@example.org",
    phone = "123456789",
)

class FakeUsersNetworkDataSource : UsersNetworkDataSource {

    var usersResult: Result<NetworkUsersResponse, NetworkError> = Ok(testNetworkResponse)
    var userResult: Result<NetworkUser, NetworkError> = Ok(testNetworkUser)

    override suspend fun getUsers(page: Int) = usersResult

    override suspend fun getUser(userId: String) = userResult
}
