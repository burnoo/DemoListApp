package dev.burnoo.demo.listapp.data.users.repository.di

import dev.burnoo.demo.listapp.data.users.network.di.usersNetworkModule
import dev.burnoo.demo.listapp.data.users.repository.NetworkUsersRepository
import dev.burnoo.demo.listapp.data.users.repository.UsersRepository
import org.koin.dsl.module

val usersRepositoryModule = module {
    includes(usersNetworkModule)
    single<UsersRepository> {
        NetworkUsersRepository(dataSource = get())
    }
}
