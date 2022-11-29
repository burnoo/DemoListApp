package dev.burnoo.demo.listapp.data.users.core.utils

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem

val testNetworkUsers = List(5) {
    NetworkUserItem(
        id = it.toString(),
        title = "mrs",
        firstName = "John $it",
        lastName = "Doe",
        picture = "https://example.org/image$it.jpg",
    )
}

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

    var usersResult: Result<List<NetworkUserItem>, NetworkError> = Ok(testNetworkUsers)
    var userResult: Result<NetworkUser, NetworkError> = Ok(testNetworkUser)

    override suspend fun getUsers() = usersResult

    override suspend fun getUser(userId: String) = userResult
}
