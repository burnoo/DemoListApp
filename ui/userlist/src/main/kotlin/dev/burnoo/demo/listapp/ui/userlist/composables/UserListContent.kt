package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.burnoo.demo.listapp.core.designsystem.composables.Loader
import dev.burnoo.demo.listapp.core.designsystem.composables.TryAgain
import dev.burnoo.demo.listapp.core.ui.collectAsStateWithLifecycle
import dev.burnoo.demo.listapp.core.ui.getViewModel
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userlist.UserListUiState
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel

@Composable
internal fun UserListContent(
    viewModel: UserListViewModel = getViewModel(),
    onUserClick: (UserId) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserListContent(
        uiState,
        onUserClick,
        onLoadMore = viewModel::fetchNextPage,
        onTryAgain = viewModel::tryAgain,
    )
}

@Composable
internal fun UserListContent(
    uiState: UserListUiState,
    onUserClick: (UserId) -> Unit,
    onLoadMore: () -> Unit,
    onTryAgain: () -> Unit,
) {
    when (uiState) {
        UserListUiState.Loading -> Loader()
        is UserListUiState.Loaded -> UserList(uiState, onUserClick, onLoadMore)
        UserListUiState.Error -> TryAgain(onTryAgain)
    }
}
