package dev.burnoo.demo.listapp.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class UserListViewModel(
    private val repository: UsersRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { fetchData() }
    }

    fun tryAgain() {
        viewModelScope.launch { fetchData() }
    }

    private suspend fun fetchData() {
        _uiState.value = UserListUiState.Loading
        val usersResult = repository.getUsers()
        _uiState.value = usersResult.fold(
            success = { UserListUiState.Loaded(it) },
            failure = { UserListUiState.Error },
        )
    }
}
