package dev.burnoo.demo.listapp.core.designsystem.images

import androidx.compose.runtime.Composable

@Composable
expect fun NetworkImage(
    url: String,
    contentDescription: String,
    modifier: androidx.compose.ui.Modifier,
    shouldLoadImagesFromNetwork: ShouldLoadImagesFromNetwork,
)
