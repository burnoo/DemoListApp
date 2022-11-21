package dev.burnoo.demo.listapp.data.users.core

import dev.burnoo.demo.listapp.data.users.core.mappers.asExternalModel
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

    override suspend fun getUsers(): List<UserItem> {
        return withContext(coroutineDispatcher) {
            dataSource.getUsers().map { it.asExternalModel() }
        }
    }

    override suspend fun getUser(userId: UserId): User {
        return withContext(coroutineDispatcher) {
            dataSource.getUser(userId.value).asExternalModel()
        }
    }
}