package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.burnoo.demo.listapp.core.data.Pager
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import kotlinx.coroutines.delay

class FakeUsersRepository : UsersRepository {

    private var usersResultCount = 0
    private var usersResults: List<Result<Pager.PagedList<UserItem>, DataError>> = listOf(
        Ok(testPagedUserList()),
    )

    private var userResultCount = 0
    private var userResults: List<Result<User, DataError>> = listOf(Ok(testUser))
    var delayMillis: Long? = null

    override fun getUserListPager() = Pager(
        fetchPagedList = {
            delayMillis?.let { delay(it) }
            usersResults[usersResultCount++ % usersResults.size]
        },
    )

    override suspend fun getUser(userId: UserId) = userResults[userResultCount++ % userResults.size]

    fun setUsersResults(vararg results: Result<Pager.PagedList<UserItem>, DataError>) {
        usersResults = results.toList()
    }

    fun setUserResults(vararg results: Result<User, DataError>) {
        userResults = results.toList()
    }
}
