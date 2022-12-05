package dev.burnoo.demo.listapp.composable.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

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
