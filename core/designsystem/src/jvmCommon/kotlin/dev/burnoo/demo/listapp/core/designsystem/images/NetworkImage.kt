package dev.burnoo.demo.listapp.core.designsystem.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
actual fun NetworkImage(
    url: String,
    contentDescription: String,
    modifier: Modifier,
    shouldLoadImagesFromNetwork: ShouldLoadImagesFromNetwork,
) {
    val shouldLoadImages = remember(shouldLoadImagesFromNetwork) {
        shouldLoadImagesFromNetwork()
    }
    val resource = if (shouldLoadImages) {
        lazyPainterResource(data = url)
    } else {
        Resource.Loading(0f)
    }
    KamelImage(
        resource = resource,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
