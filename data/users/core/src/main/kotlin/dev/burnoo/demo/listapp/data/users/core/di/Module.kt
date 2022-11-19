package dev.burnoo.demo.listapp.data.users.core.di

import dev.burnoo.demo.listapp.data.users.core.NetworkUsersRepository
import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import dev.burnoo.demo.listapp.data.users.network.di.usersNetworkModule
import org.koin.dsl.module

val dataUsersModule = module {
    includes(usersNetworkModule)
    single<UsersRepository> {
        NetworkUsersRepository(dataSource = get())
    }
}
