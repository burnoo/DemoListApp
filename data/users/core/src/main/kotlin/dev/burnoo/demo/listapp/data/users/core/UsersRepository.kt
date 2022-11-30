package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.Users

interface UsersRepository {

    suspend fun getUsers(page: Int): Result<Users, DataError>

    suspend fun getUser(userId: UserId): Result<User, DataError>
}
