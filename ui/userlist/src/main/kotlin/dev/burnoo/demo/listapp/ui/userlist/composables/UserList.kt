package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem

@Composable
internal fun UserList(users: List<UserItem>, onUserClick: (UserId) -> Unit) {
    LazyColumn {
        items(users, key = { it.id.value }) { user ->
            UserRow(user, onUserClick)
        }
    }
}
