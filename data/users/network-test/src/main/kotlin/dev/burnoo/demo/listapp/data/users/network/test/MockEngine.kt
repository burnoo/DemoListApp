package dev.burnoo.demo.listapp.data.users.network.test

import dev.burnoo.demo.listapp.data.users.network.api.BASE_URL
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

fun createMockEngine(
    getUsers: MockRequestHandleScope.() -> HttpResponseData = {
        respondJson(getUsersResponse())
    },
    getUser: MockRequestHandleScope.() -> HttpResponseData = {
        respondJson(getUserResponse())
    },
): HttpClientEngine = MockEngine { request ->
    when (request.url.toString()) {
        "$BASE_URL/user" -> getUsers()
        "$BASE_URL/user/${TestApiUser.id}" -> getUser()
        else -> throw RuntimeException("Unsupported url")
    }
}

private fun MockRequestHandleScope.respondJson(json: String): HttpResponseData {
    return respond(
        content = ByteReadChannel(json),
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}
