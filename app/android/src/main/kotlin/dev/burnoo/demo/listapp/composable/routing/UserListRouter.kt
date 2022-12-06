package dev.burnoo.demo.listapp.composable.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userlist.UserListScreen

object UserListRoutes {
    const val root = "user-list"
    const val main = "main"
}

fun NavGraphBuilder.userListRouter(onUserClick: (UserId) -> Unit) {
    navigation(startDestination = UserListRoutes.main, route = UserListRoutes.root) {
        composable(UserListRoutes.main) { UserListScreen(onUserClick) }
    }
}
