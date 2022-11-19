package dev.burnoo.demo.listapp.userlist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.burnoo.demo.listapp.core.designsystem.AppTheme
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import dev.burnoo.demo.listapp.feature.userlist.R

@Composable
internal fun UserRow(user: UserItem, onClick: (UserId) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = user.photoUrl,
            contentDescription = stringResource(
                R.string.user_row_photo_content_description,
                user.firstName,
                user.lastName,
            ),
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(
                id = R.string.user_row_name,
                user.title,
                user.firstName,
                user.lastName,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserRowPreview() {
    AppTheme {
        UserRow(
            user = UserItem(
                id = UserId("1"),
                title = "mrs",
                firstName = "John",
                lastName = "Doe",
                photoUrl = "https://example.org/image.jpg",
            ),
            onClick = { },
        )
    }
}
