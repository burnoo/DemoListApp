package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.burnoo.demo.listapp.core.designsystem.theme.AppTheme
import dev.burnoo.demo.listapp.core.utils.TitleParser
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.ui.userdetails.R

@Composable
internal fun UserDetails(user: User) {
    val titleParser = remember { TitleParser() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = user.photoUrl,
            contentDescription = stringResource(
                id = R.string.user_details_photo_content_description,
                user.firstName,
                user.lastName,
            ),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(
                id = R.string.user_details_name,
                titleParser.parse(user.title),
                user.firstName,
                user.lastName,
            ),
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
    }
}

@Preview(showBackground = true)
@Composable
internal fun UserDetailsPreview() {
    AppTheme {
        UserDetails(
            user = User(
                title = "mr",
                firstName = "John",
                lastName = "Doe",
                photoUrl = "https://example.org/image0.jpg",
                gender = "male",
                email = "test@example.org",
                phone = "123456789",
            ),
        )
    }
}
