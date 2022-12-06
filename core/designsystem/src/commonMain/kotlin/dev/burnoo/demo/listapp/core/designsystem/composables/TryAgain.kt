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
import androidx.compose.ui.unit.dp

@Composable
fun TryAgain(onTryAgain: () -> Unit, onGoBack: (() -> Unit?)? = null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Request failed")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onTryAgain, modifier = Modifier.testTag("Try again button")) {
                Text(text = "Try again")
            }

            if (onGoBack != null) {
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { onGoBack() }, modifier = Modifier.testTag("Go back button")) {
                    Text(text = "Go back")
                }
            }
        }
    }
}
