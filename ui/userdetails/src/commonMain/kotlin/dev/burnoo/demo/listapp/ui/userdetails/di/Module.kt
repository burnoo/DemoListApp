package dev.burnoo.demo.listapp.ui.userdetails.di

import dev.burnoo.demo.listapp.core.ui.viewModel
import dev.burnoo.demo.listapp.data.users.core.di.dataUsersModule
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsViewModel
import org.koin.dsl.module

val uiUserDetailsModule = module {
    includes(dataUsersModule)
    viewModel { (userId: UserId) -> UserDetailsViewModel(repository = get(), userId = userId) }
}
