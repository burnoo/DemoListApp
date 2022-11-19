package dev.burnoo.demo.listapp.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch {
            val users = repository.getUsers()
            _uiState.value = UserListUiState.Loaded(users)
        }
    }
}
