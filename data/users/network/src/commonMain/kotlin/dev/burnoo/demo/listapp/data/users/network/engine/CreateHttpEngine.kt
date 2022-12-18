package dev.burnoo.demo.listapp.data.users.network.engine

import io.ktor.client.engine.HttpClientEngine

internal expect fun createHttpClientEngine(): HttpClientEngine
