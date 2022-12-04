package dev.burnoo.demo.listapp.ui.userlist.di

import dev.burnoo.demo.listapp.core.ui.viewModel
import dev.burnoo.demo.listapp.data.users.core.di.dataUsersModule
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel
import org.koin.dsl.module

val uiUserListModule = module {
    includes(dataUsersModule)
    viewModel { UserListViewModel(repository = get()) }
}
