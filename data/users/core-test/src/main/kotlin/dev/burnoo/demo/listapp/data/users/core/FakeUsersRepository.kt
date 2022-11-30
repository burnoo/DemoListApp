package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import dev.burnoo.demo.listapp.data.users.model.Users

class FakeUsersRepository : UsersRepository {

    private var usersResultCount = 0
    private var usersResults: List<Result<Users, DataError>> = listOf(
        Ok(Users(list = testUserList, isLastPage = false)),
    )

    private var userResultCount = 0
    private var userResults: List<Result<User, DataError>> = listOf(Ok(testUser))

    override suspend fun getUsers(page: Int) = usersResults[usersResultCount++ % usersResults.size]

    override suspend fun getUser(userId: UserId) = userResults[userResultCount++ % userResults.size]

    fun setUsersResults(vararg results: Result<List<UserItem>, DataError>) {
        usersResults = results.map { result ->
            result.map { Users(list = it, isLastPage = false) }
        }
    }

    fun setUserResults(vararg results: Result<User, DataError>) {
        userResults = results.toList()
    }
}
