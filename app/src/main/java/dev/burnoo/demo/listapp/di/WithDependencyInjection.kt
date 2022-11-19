package dev.burnoo.demo.listapp.di

import androidx.compose.runtime.Composable
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.userlist.di.uiUserListModule

@Composable
fun WithDependencyInjection(content: @Composable () -> Unit) {
    Koin(
        appDeclaration = { modules(uiUserListModule) },
        content = content,
    )
}
