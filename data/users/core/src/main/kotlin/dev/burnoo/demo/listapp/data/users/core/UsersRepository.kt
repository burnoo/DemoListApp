package dev.burnoo.demo.listapp.data.users.core

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface UsersRepository {

    fun getUserListPager(): Pager<UserItem, DataError>

    suspend fun getUser(userId: UserId): Result<User, DataError>
}

// TODO: move this class to :core:data and add tests to it
class Pager<V, E>(
    private val fetchPagedList: suspend (Int) -> Result<PagedList<V>, E>,
) {

    private var currentList: List<V> = emptyList()
    private var nextPage = 0

    private val _status = MutableSharedFlow<Status<V, E>>(replay = 1)
    val status = _status.asSharedFlow()

    suspend fun loadPage() {
        val nextPageListResult = fetchPagedList(nextPage)
        nextPageListResult.onSuccess {
            nextPage++
            currentList = currentList + it.list
        }
        _status.emit(
            Status(
                lastResult = nextPageListResult.map { },
                currentList = currentList,
                isLastPage = nextPageListResult.fold(
                    success = { it.isLastPage },
                    failure = { false },
                ),
            ),
        )
    }

    data class Status<V, E>(
        val lastResult: Result<Unit, E>,
        val currentList: List<V>,
        val isLastPage: Boolean,
    )

    data class PagedList<V>(
        val list: List<V>,
        val isLastPage: Boolean,
    )
}
