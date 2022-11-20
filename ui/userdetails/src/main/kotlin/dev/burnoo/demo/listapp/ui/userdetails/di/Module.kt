package dev.burnoo.demo.listapp.ui.userdetails.di

import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.domain.users.di.domainUsersModule
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiUserDetailsModule = module {
    includes(domainUsersModule)
    viewModel { (userId: UserId) -> UserDetailsViewModel(getUserUseCase = get(), userId = userId) }
}
