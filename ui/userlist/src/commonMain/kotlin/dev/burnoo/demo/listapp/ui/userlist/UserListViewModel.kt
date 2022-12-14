package dev.burnoo.demo.listapp.ui.userlist

import com.github.michaelbull.result.fold
import dev.burnoo.demo.listapp.core.ui.ViewModel
import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class UserListViewModel(
    repository: UsersRepository,
) : ViewModel() {

    private val pager = repository.getUserListPager()
    private var isNextPageLoading = atomic(false)

    val uiState = pager.status.map { status ->
        status.lastResult.fold(
            success = { UserListUiState.Loaded(status.fullList, status.isLastPage) },
            failure = { UserListUiState.Error },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserListUiState.Loading,
    )

    init {
        viewModelScope.launch { pager.loadPage() }
    }

    fun tryAgain() {
        viewModelScope.launch { pager.loadPage() }
    }

    fun fetchNextPage() {
        if (isNextPageLoading.value) return
        viewModelScope.launch {
            isNextPageLoading.value = true
            pager.loadPage()
            isNextPageLoading.value = false
        }
    }
}
