package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapEither
import dev.burnoo.demo.listapp.data.users.core.mappers.asExternalModel
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.Users
import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UsersRemoteRepository(
    private val dataSource: UsersNetworkDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UsersRepository {

    override suspend fun getUsers(page: Int): Result<Users, DataError> {
        return withContext(coroutineDispatcher) {
            dataSource.getUsers(page).mapEither(
                success = { users ->
                    val userList = users.data.map { it.asExternalModel() }
                    val isLastPage = (users.page + 1) * users.limit >= users.total
                    Users(list = userList, isLastPage = isLastPage)
                },
                failure = { DataError },
            )
        }
    }

    override suspend fun getUser(userId: UserId): Result<User, DataError> {
        return withContext(coroutineDispatcher) {
            dataSource.getUser(userId.value).mapEither(
                success = { it.asExternalModel() },
                failure = { DataError },
            )
        }
    }
}
