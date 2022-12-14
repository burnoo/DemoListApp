package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.burnoo.demo.listapp.core.designsystem.composables.Loader
import dev.burnoo.demo.listapp.core.designsystem.composables.TryAgain
import dev.burnoo.demo.listapp.core.ui.collectAsStateWithLifecycle
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsUiState
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsViewModel

@Composable
internal fun UserDetailsContent(viewModel: UserDetailsViewModel, onGoBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserDetailsContent(uiState, onTryAgain = viewModel::tryAgain, onGoBack = onGoBack)
}

@Composable
internal fun UserDetailsContent(uiState: UserDetailsUiState, onTryAgain: () -> Unit, onGoBack: () -> Unit) {
    when (uiState) {
        UserDetailsUiState.Loading -> Loader()
        UserDetailsUiState.Error -> TryAgain(onTryAgain, onGoBack)
        is UserDetailsUiState.Loaded -> UserDetails(uiState.user, onGoBack)
    }
}
