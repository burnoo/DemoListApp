package dev.burnoo.demo.listapp.userlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object UserListRoutes {
    const val root = "user-list"
    internal const val main = "main"
}

fun NavGraphBuilder.userListRouter() {
    navigation(startDestination = UserListRoutes.main, route = UserListRoutes.root) {
        composable(UserListRoutes.main) { UserListScreen() }
    }
}
