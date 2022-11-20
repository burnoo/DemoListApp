package dev.burnoo.demo.listapp.domain.users.di

import dev.burnoo.demo.listapp.data.users.core.di.dataUsersModule
import dev.burnoo.demo.listapp.domain.users.GetUserUseCase
import dev.burnoo.demo.listapp.domain.users.GetUsersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainUsersModule = module {
    includes(dataUsersModule)

    factoryOf(::GetUserUseCase)
    factoryOf(::GetUsersUseCase)
}
