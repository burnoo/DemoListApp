package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.get
import dev.burnoo.demo.listapp.core.designsystem.images.NetworkImage
import dev.burnoo.demo.listapp.core.designsystem.images.ShouldLoadImagesFromNetwork
import dev.burnoo.demo.listapp.core.utils.TitleParser
import dev.burnoo.demo.listapp.data.users.model.User

@Composable
internal fun UserDetails(
    user: User,
    onGoBack: () -> Unit,
    shouldLoadImagesFromNetwork: ShouldLoadImagesFromNetwork = get(),
) {
    val titleParser = remember { TitleParser() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.size(150.dp)) {
            NetworkImage(
                url = user.photoUrl,
                contentDescription = "Photo of ${user.firstName} ${user.lastName}",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                shouldLoadImagesFromNetwork = shouldLoadImagesFromNetwork,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${titleParser.parse(user.title)} ${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = user.gender,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = user.phone,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onGoBack() }, modifier = Modifier.testTag("Go back button")) {
            Text(text = "Go back")
        }
    }
}
