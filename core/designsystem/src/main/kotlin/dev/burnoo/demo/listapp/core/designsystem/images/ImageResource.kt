package dev.burnoo.demo.listapp.core.designsystem.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import io.kamel.core.Resource
import io.kamel.image.lazyPainterResource

interface ImageResource {

    @Composable
    operator fun invoke(url: String): Resource<Painter>
}

class NetworkImageResource : ImageResource {

    @Composable
    override operator fun invoke(url: String) = lazyPainterResource(data = url)
}
