package dev.burnoo.demo.listapp.ui.userdetails

import dev.burnoo.demo.listapp.data.users.model.User

internal sealed interface UserDetailsUiState {

    object Loading : UserDetailsUiState
    object Error : UserDetailsUiState
    data class Loaded(val user: User) : UserDetailsUiState
}
