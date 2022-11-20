package dev.burnoo.demo.listapp.ui.userlist.di

import dev.burnoo.demo.listapp.domain.users.di.domainUsersModule
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiUserListModule = module {
    includes(domainUsersModule)
    viewModelOf(::UserListViewModel)
}
