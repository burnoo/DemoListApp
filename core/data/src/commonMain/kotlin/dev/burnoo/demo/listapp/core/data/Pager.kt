package dev.burnoo.demo.listapp.core.data

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Pager<V, E>(
    private val fetchPagedList: suspend (Int) -> Result<PagedList<V>, E>,
) {

    private var fullList: List<V> = emptyList()
    private var nextPage = 0
    private val mutex = Mutex()

    private val _status = MutableSharedFlow<Status<V, E>>(replay = 1)
    val status = _status.asSharedFlow()

    suspend fun loadPage() = mutex.withLock {
        val nextPageListResult = fetchPagedList(nextPage)
        nextPageListResult.onSuccess {
            nextPage++
            fullList = fullList + it.list
        }
        _status.emit(
            Status(
                lastResult = nextPageListResult.map { },
                fullList = fullList,
                isLastPage = nextPageListResult.fold(
                    success = { it.isLastPage },
                    failure = { false },
                ),
            ),
        )
    }

    data class Status<V, E>(
        val lastResult: Result<Unit, E>,
        val fullList: List<V>,
        val isLastPage: Boolean,
    )

    data class PagedList<V>(
        val list: List<V>,
        val isLastPage: Boolean,
    )
}
