package dev.burnoo.demo.listapp.core.designsystem.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.burnoo.demo.listapp.ui.theme.R

@Composable
fun TryAgain(onTryAgain: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = stringResource(id = R.string.try_again_error_message))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onTryAgain, modifier = Modifier.testTag("Try again button")) {
                Text(text = stringResource(id = R.string.try_again_button))
            }
        }
    }
}
