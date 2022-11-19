package dev.burnoo.demo.listapp.userlist.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.burnoo.cokoin.viewmodel.getViewModel
import dev.burnoo.demo.listapp.userlist.UserListUiState
import dev.burnoo.demo.listapp.userlist.UserListViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun UserListContent(viewModel: UserListViewModel = getViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserListContent(uiState)
}

@Composable
private fun UserListContent(uiState: UserListUiState) {
    when (uiState) {
        UserListUiState.Loading -> Loader()
        is UserListUiState.Loaded -> UserList(uiState.users)
    }
}
