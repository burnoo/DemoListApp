package dev.burnoo.demo.listapp.data.users.network.di

import dev.burnoo.demo.listapp.data.users.network.UsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.network.api.UsersApi
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val usersNetworkModule = module {
    factory<HttpClientEngine> { OkHttpEngine(OkHttpConfig()) }
    singleOf(::UsersApi) { bind<UsersNetworkDataSource>() }
}
