package dev.burnoo.demo.listapp.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsRoutes
import dev.burnoo.demo.listapp.ui.userdetails.userDetailsRouter
import dev.burnoo.demo.listapp.ui.userlist.UserListRoutes
import dev.burnoo.demo.listapp.ui.userlist.userListRouter

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = UserListRoutes.root) {
        userListRouter(
            onUserClick = { userId ->
                navController.navigate(UserDetailsRoutes.root + "/${userId.value}")
            },
        )

        userDetailsRouter()
    }
}
