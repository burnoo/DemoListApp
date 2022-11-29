package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem

class FakeUsersRepository : UsersRepository {

    private var usersResultCount = 0
    private var usersResults: List<Result<List<UserItem>, DataError>> = listOf(Ok(testUsers))

    private var userResultCount = 0
    private var userResults: List<Result<User, DataError>> = listOf(Ok(testUser))

    override suspend fun getUsers(page: Int) = usersResults[usersResultCount++ % usersResults.size]

    override suspend fun getUser(userId: UserId) = userResults[userResultCount++ % userResults.size]

    fun setUsersResults(vararg results: Result<List<UserItem>, DataError>) {
        usersResults = results.toList()
    }

    fun setUserResults(vararg results: Result<User, DataError>) {
        userResults = results.toList()
    }
}
