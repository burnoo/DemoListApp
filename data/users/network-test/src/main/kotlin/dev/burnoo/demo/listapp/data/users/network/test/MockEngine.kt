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

const val TEST_USER_ID = "TEST_USER_ID"
const val TEST_FIRST_NAME = "Jon"
const val TEST_LAST_NAME = "Doe"

fun createMockEngine(): HttpClientEngine = MockEngine { request ->
    when (request.url.toString()) {
        "$BASE_URL/user/$TEST_USER_ID" -> respondJson(
            getUserResponse(TEST_FIRST_NAME, TEST_LAST_NAME),
        )

        "$BASE_URL/user" -> respondJson(getUsersResponse(firstUserId = TEST_USER_ID))
        else -> throw RuntimeException("Unsupported url")
    }
}

private fun MockRequestHandleScope.respondJson(json: String): HttpResponseData {
    return respond(
        content = ByteReadChannel(json),
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}
