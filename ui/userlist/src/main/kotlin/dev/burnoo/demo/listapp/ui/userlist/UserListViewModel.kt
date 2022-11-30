package dev.burnoo.demo.listapp.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import dev.burnoo.demo.listapp.data.users.model.UserItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class UserListViewModel(
    private val repository: UsersRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var nextPage = 0
    private var loadedUsers = listOf<UserItem>()

    init {
        viewModelScope.launch { fetchData(page = 0) }
    }

    fun tryAgain() {
        viewModelScope.launch { fetchData(page = nextPage) }
    }

    fun fetchNextPage() {
        viewModelScope.launch { fetchData(page = nextPage) }
    }

    private suspend fun fetchData(page: Int) {
        if (uiState.value !is UserListUiState.Loaded) {
            _uiState.value = UserListUiState.Loading
        }
        val usersResult = repository.getUsers(page)
        _uiState.value = usersResult.fold(
            success = {
                loadedUsers = loadedUsers + it
                nextPage++
                UserListUiState.Loaded(loadedUsers)
            },
            failure = { UserListUiState.Error },
        )
    }
}
