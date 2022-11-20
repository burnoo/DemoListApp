package dev.burnoo.demo.listapp.ui.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.domain.users.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class UserDetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
    userId: UserId,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = getUserUseCase(userId)
            _uiState.value = UserDetailsUiState.Loaded(user)
        }
    }
}
