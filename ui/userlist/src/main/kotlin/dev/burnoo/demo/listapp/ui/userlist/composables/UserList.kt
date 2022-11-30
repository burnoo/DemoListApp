package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem

@Composable
internal fun UserList(
    users: List<UserItem>,
    onUserClick: (UserId) -> Unit,
    onLoadMore: () -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(users, key = { it.id.value }) { user ->
            UserRow(user, onUserClick)
        }

        item { MoreLoader() }
    }

    LoadMoreEffect(listState = listState, onLoadMore = onLoadMore)
}

@Composable
internal fun LoadMoreEffect(
    listState: LazyListState,
    onLoadMore: () -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true
            lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1
        }
    }
    LaunchedEffect(Unit) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) onLoadMore()
            }
    }
}
