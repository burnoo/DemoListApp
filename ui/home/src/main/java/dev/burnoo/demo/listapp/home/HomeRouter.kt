package dev.burnoo.demo.listapp.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeRouter() {
    navigation(startDestination = "main", route = "home") {
        composable("main") { MainScreen() }
    }
}
