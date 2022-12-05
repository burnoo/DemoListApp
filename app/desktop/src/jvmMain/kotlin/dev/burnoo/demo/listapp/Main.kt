package dev.burnoo.demo.listapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.burnoo.demo.listapp.core.designsystem.theme.AppTheme
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.di.WithDependencyInjection
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsScreen
import dev.burnoo.demo.listapp.ui.userlist.UserListScreen

fun main() = application {
    WithDependencyInjection {
        Window(
            onCloseRequest = ::exitApplication,
            title = "DemoListApp",
        ) {
            AppTheme {
                var userId by remember { mutableStateOf<UserId?>(null) }

                when (val currentUserId = userId) {
                    null -> UserListScreen(onUserClick = { userId = it })
                    else -> UserDetailsScreen(
                        userId = currentUserId.value,
                        onGoBack = { userId = null },
                    )
                }
            }
        }
    }
}
