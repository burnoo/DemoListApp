package dev.burnoo.demo.listapp.data.users.network.di

import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.api.UsersApi
import dev.burnoo.demo.listapp.data.users.network.engine.createHttpClientEngine
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val usersNetworkModule = module {
    single { createHttpClientEngine() }
    singleOf(::UsersApi) { bind<UsersNetworkDataSource>() }
}
