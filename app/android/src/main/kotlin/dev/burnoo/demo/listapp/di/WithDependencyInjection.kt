package dev.burnoo.demo.listapp.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.core.designsystem.images.ImageResource
import dev.burnoo.demo.listapp.core.designsystem.images.NetworkImageResource
import dev.burnoo.demo.listapp.ui.userdetails.di.uiUserDetailsModule
import dev.burnoo.demo.listapp.ui.userlist.di.uiUserListModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Composable
fun WithDependencyInjection(content: @Composable () -> Unit) {
    val appContext = LocalContext.current.applicationContext
    Koin(
        appDeclaration = {
            modules(
                module {
                    androidContext(appContext)
                    single<ImageResource> { NetworkImageResource() }
                },
                uiUserListModule,
                uiUserDetailsModule,
            )
        },
        content = content,
    )
}
