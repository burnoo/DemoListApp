package dev.burnoo.demo.listapp.data.users.repository.utils

import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
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

    override suspend fun getUsers(): List<NetworkUserItem> = testNetworkUsers

    override suspend fun getUser(userId: String) = testNetworkUser
}
