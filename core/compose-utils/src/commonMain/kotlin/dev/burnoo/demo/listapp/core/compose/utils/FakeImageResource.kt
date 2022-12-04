package dev.burnoo.demo.listapp.core.compose.utils

import androidx.compose.runtime.Composable
import dev.burnoo.demo.listapp.core.designsystem.images.ImageResource
import io.kamel.core.Resource

class FakeImageResource : ImageResource {

    @Composable
    override operator fun invoke(url: String) = Resource.Loading(0f)
}
