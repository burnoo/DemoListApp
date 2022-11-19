package dev.burnoo.demo.listapp.ui.userdetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.burnoo.cokoin.viewmodel.getViewModel
import dev.burnoo.demo.listapp.data.users.model.UserId
import org.koin.core.parameter.parametersOf

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
            val viewModel = getViewModel<UserDetailsViewModel>(parameters = {
                parametersOf(UserId(value = userId))
            },)
            UserDetailsScreen(viewModel)
        }
    }
}
