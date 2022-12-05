package dev.burnoo.demo.listapp.di

import androidx.compose.runtime.Composable
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.core.designsystem.images.ImageResource
import dev.burnoo.demo.listapp.core.designsystem.images.NetworkImageResource
import dev.burnoo.demo.listapp.ui.userdetails.di.uiUserDetailsModule
import dev.burnoo.demo.listapp.ui.userlist.di.uiUserListModule
import org.koin.dsl.module

@Composable
fun WithDependencyInjection(content: @Composable () -> Unit) {
    Koin(
        appDeclaration = {
            modules(
                module {
                    single<ImageResource> { NetworkImageResource() }
                },
                uiUserListModule,
                uiUserDetailsModule,
            )
        },
        content = content,
    )
}