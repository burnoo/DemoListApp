package dev.burnoo.demo.listapp.core.compose.utils.di

import dev.burnoo.demo.listapp.core.designsystem.images.ShouldLoadImagesFromNetwork
import org.koin.dsl.module

val coreComposeUtilsModule = module {
    single { ShouldLoadImagesFromNetwork { false } }
}
