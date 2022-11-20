package dev.burnoo.demo.listapp.core.compose.utils.di

import coil.ImageLoader
import dev.burnoo.demo.listapp.core.compose.utils.FakeImageLoader
import org.koin.dsl.module

val coreComposeUtilsModule = module {
    single<ImageLoader> { FakeImageLoader() }
}
