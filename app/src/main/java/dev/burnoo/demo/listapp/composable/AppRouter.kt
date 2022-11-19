package dev.burnoo.demo.listapp.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.burnoo.demo.listapp.userlist.UserListRoutes
import dev.burnoo.demo.listapp.userlist.userListRouter

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = UserListRoutes.root) {
        userListRouter()
    }
}
