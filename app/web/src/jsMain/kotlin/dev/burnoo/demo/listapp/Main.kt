package dev.burnoo.demo.listapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.burnoo.demo.listapp.core.designsystem.theme.AppTheme
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.di.WithDependencyInjection
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsScreen
import dev.burnoo.demo.listapp.ui.userlist.UserListScreen
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("DemoListApp") {
            WithDependencyInjection { App() }
        }
    }
}

@Suppress("DEPRECATION")
@Composable
internal fun App() {
    AppTheme {
        var userId by remember { mutableStateOf<UserId?>(null) }
        UserListScreen(onUserClick = { userId = it })

        userId?.let {
            UserDetailsScreen(
                userId = it.value,
                onGoBack = { userId = null },
            )
        }
    }
}
