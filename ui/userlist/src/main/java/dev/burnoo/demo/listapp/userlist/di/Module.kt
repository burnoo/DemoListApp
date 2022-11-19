package dev.burnoo.demo.listapp.userlist.di

import dev.burnoo.demo.listapp.data.users.repository.di.usersRepositoryModule
import dev.burnoo.demo.listapp.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiUserListModule = module {
    includes(usersRepositoryModule)
    viewModelOf(::UserListViewModel)
}
