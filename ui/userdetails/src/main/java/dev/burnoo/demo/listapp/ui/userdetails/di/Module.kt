package dev.burnoo.demo.listapp.ui.userdetails.di

import dev.burnoo.demo.listapp.data.users.core.di.usersRepositoryModule
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiUserDetailsModule = module {
    includes(usersRepositoryModule)
    viewModel { (userId: UserId) -> UserDetailsViewModel(repository = get(), userId = userId) }
}
