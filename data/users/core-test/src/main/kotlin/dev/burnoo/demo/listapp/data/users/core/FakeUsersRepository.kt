package dev.burnoo.demo.listapp.data.users.core

import dev.burnoo.demo.listapp.data.users.model.UserId

class FakeUsersRepository : UsersRepository {

    override suspend fun getUsers() = testUsers

    override suspend fun getUser(userId: UserId) = testUser
}
