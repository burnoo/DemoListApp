package dev.burnoo.demo.listapp.core.compose.utils.di

import dev.burnoo.demo.listapp.core.compose.utils.FakeImageResource
import dev.burnoo.demo.listapp.core.designsystem.images.ImageResource
import org.koin.dsl.module

val coreComposeUtilsModule = module {
    single<ImageResource> { FakeImageResource() }
}
