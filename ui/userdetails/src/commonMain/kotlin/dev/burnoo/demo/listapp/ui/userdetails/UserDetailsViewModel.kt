package dev.burnoo.demo.listapp.ui.userdetails

import com.github.michaelbull.result.fold
import dev.burnoo.demo.listapp.core.ui.ViewModel
import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import dev.burnoo.demo.listapp.data.users.model.UserId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UserDetailsViewModel(
    private val repository: UsersRepository,
    private val userId: UserId,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { fetchData() }
    }

    fun tryAgain() {
        viewModelScope.launch { fetchData() }
    }

    private suspend fun fetchData() {
        _uiState.update { UserDetailsUiState.Loading }
        val userResult = repository.getUser(userId)
        _uiState.update {
            userResult.fold(
                success = { UserDetailsUiState.Loaded(it) },
                failure = { UserDetailsUiState.Error },
            )
        }
    }
}
