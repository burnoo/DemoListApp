package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.burnoo.demo.listapp.core.designsystem.composables.Loader
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsUiState
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun UserDetailsContent(viewModel: UserDetailsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserDetailsContent(uiState)
}

@Composable
private fun UserDetailsContent(uiState: UserDetailsUiState) {
    when (uiState) {
        UserDetailsUiState.Loading -> Loader()
        is UserDetailsUiState.Loaded -> UserDetails(uiState.user)
    }
}
