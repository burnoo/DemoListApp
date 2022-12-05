package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.get
import dev.burnoo.demo.listapp.core.designsystem.images.ImageResource
import dev.burnoo.demo.listapp.core.utils.TitleParser
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import io.kamel.image.KamelImage

@Composable
internal fun UserRow(
    user: UserItem,
    onClick: (UserId) -> Unit,
    imageResource: ImageResource = get(),
) {
    val titleParser = remember { TitleParser() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) }
            .padding(12.dp)
            .testTag("UserRow"),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(60.dp)) {
            KamelImage(
                resource = imageResource(url = user.photoUrl),
                contentDescription = "Photo of ${user.firstName} ${user.lastName}",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "${titleParser.parse(user.title)} ${user.firstName} ${user.lastName}",
        )
    }
}
