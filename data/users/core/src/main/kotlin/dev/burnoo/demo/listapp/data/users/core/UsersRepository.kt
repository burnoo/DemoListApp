package dev.burnoo.demo.listapp.data.users.core

import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem

interface UsersRepository {

    suspend fun getUsers(): List<UserItem>

    suspend fun getUser(userId: UserId): User
}
