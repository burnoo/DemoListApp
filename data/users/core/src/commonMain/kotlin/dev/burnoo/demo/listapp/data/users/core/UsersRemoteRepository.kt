package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapEither
import dev.burnoo.demo.listapp.core.data.Pager
import dev.burnoo.demo.listapp.data.users.core.mappers.asExternalModel
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UsersRemoteRepository(
    private val dataSource: UsersNetworkDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UsersRepository {

    override fun getUserListPager(): Pager<UserItem, DataError> {
        return Pager(fetchPagedList = { page -> getUsersPagedList(page) })
    }

    override suspend fun getUser(userId: UserId): Result<User, DataError> {
        return withContext(coroutineDispatcher) {
            dataSource.getUser(userId.value).mapEither(
                success = { it.asExternalModel() },
                failure = { DataError },
            )
        }
    }

    private suspend fun getUsersPagedList(page: Int): Result<Pager.PagedList<UserItem>, DataError> {
        return withContext(coroutineDispatcher) {
            dataSource.getUsers(page)
        }.mapEither(
            success = { response ->
                val isLastPage = calculateIsLastPage(response.page, response.limit, response.total)
                val userList = response.data.map { it.asExternalModel() }
                Pager.PagedList(userList, isLastPage)
            },
            failure = { DataError },
        )
    }

    private fun calculateIsLastPage(page: Int, limit: Int, total: Int): Boolean {
        return (page + 1) * limit >= total
    }
}
