package dev.burnoo.demo.listapp.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.burnoo.demo.listapp.feature.userlist.R
import dev.burnoo.demo.listapp.userlist.composables.UserListContent

@Composable
internal fun UserListScreen() {
    Scaffold(
        topBar = { TopBar() },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            UserListContent()
        }
    }
}

@Composable
private fun TopBar() {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.title)) })
}
