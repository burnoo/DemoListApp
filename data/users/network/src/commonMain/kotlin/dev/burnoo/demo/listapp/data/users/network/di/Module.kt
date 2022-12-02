package dev.burnoo.demo.listapp.data.users.network.di

import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.api.UsersApi
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val usersNetworkModule = module {
    factory { CIO.create() }
    singleOf(::UsersApi) { bind<UsersNetworkDataSource>() }
}
