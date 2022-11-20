package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.burnoo.cokoin.viewmodel.getViewModel
import dev.burnoo.demo.listapp.core.designsystem.composables.Loader
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userlist.UserListUiState
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun UserListContent(
    viewModel: UserListViewModel = getViewModel(),
    onUserClick: (UserId) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserListContent(uiState, onUserClick)
}

@Composable
internal fun UserListContent(uiState: UserListUiState, onUserClick: (UserId) -> Unit) {
    when (uiState) {
        UserListUiState.Loading -> Loader()
        is UserListUiState.Loaded -> UserList(uiState.users, onUserClick)
    }
}
