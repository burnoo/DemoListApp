package dev.burnoo.demo.listapp.composable.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsScreen

internal object UserDetailsKeys {
    const val userId = "userId"
}

object UserDetailsRoutes {
    const val root = "user-details"
    internal const val main = "main/{${UserDetailsKeys.userId}}"
}

fun NavGraphBuilder.userDetailsRouter() {
    navigation(
        startDestination = UserDetailsRoutes.main,
        route = UserDetailsRoutes.root + "/{${UserDetailsKeys.userId}}",
    ) {
        composable(
            UserDetailsRoutes.main,
            arguments = listOf(navArgument(UserDetailsKeys.userId) { type = NavType.StringType }),
        ) {
            val userId = it.arguments!!.getString(UserDetailsKeys.userId)!!
            UserDetailsScreen(userId)
        }
    }
}
