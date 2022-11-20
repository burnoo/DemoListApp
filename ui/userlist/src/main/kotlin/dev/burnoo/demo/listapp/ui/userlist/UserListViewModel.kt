package dev.burnoo.demo.listapp.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.burnoo.demo.listapp.domain.users.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val users = getUsersUseCase()
            _uiState.value = UserListUiState.Loaded(users)
        }
    }
}
