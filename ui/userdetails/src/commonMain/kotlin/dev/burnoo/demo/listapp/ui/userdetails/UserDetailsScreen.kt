package dev.burnoo.demo.listapp.ui.userdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.burnoo.demo.listapp.core.ui.getViewModel
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userdetails.composables.UserDetailsContent
import org.koin.core.parameter.parametersOf

@Composable
fun UserDetailsScreen(userId: String, onGoBack: () -> Unit) {
    val viewModel = getViewModel<UserDetailsViewModel>(parameters = {
        parametersOf(UserId(value = userId))
    },)
    Scaffold(
        topBar = { TopBar() },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            UserDetailsContent(viewModel, onGoBack)
        }
    }
}

@Composable
private fun TopBar() {
    CenterAlignedTopAppBar(title = { Text(text = "User details") })
}
