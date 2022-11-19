package dev.burnoo.demo.listapp.ui.userlist.di

import dev.burnoo.demo.listapp.data.users.core.di.dataUsersModule
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiUserListModule = module {
    includes(dataUsersModule)
    viewModelOf(::UserListViewModel)
}
