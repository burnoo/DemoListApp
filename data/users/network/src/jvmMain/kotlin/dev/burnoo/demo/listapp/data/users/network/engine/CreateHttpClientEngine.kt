package dev.burnoo.demo.listapp.data.users.network.engine

import io.ktor.client.engine.cio.CIO

internal actual fun createHttpClientEngine() = CIO.create()
