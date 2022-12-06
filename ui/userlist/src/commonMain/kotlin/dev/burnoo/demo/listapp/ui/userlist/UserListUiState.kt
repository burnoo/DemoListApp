package dev.burnoo.demo.listapp.ui.userlist

import dev.burnoo.demo.listapp.data.users.model.UserItem

internal sealed interface UserListUiState {

    object Loading : UserListUiState
    object Error : UserListUiState
    data class Loaded(val users: List<UserItem>, val isLastPage: Boolean) : UserListUiState
}
