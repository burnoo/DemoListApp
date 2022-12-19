package dev.burnoo.demo.listapp.core.designsystem.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.seiko.imageloader.rememberAsyncImagePainter

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
            val painter = rememberAsyncImagePainter("https://api.codetabs.com/v1/proxy?quest=$url")
            Image(painter, contentDescription, modifier = Modifier.fillMaxSize())
        }
    }
}
