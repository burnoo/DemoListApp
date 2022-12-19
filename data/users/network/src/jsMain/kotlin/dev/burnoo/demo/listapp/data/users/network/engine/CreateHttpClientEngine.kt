package dev.burnoo.demo.listapp.data.users.network.engine

import io.ktor.client.engine.js.Js

internal actual fun createHttpClientEngine() = Js.create()
