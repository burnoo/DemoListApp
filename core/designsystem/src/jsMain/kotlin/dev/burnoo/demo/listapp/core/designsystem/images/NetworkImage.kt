package dev.burnoo.demo.listapp.core.designsystem.images

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.web.dom.Img

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
    Box(modifier) {
        if (shouldLoadImages) {
            Img(
                src = url,
                alt = contentDescription,
                attrs = {
                    attr(attr = "width", value = "100%")
                    attr(attr = "height", value = "100%")
                },
            )
        }
    }
}
