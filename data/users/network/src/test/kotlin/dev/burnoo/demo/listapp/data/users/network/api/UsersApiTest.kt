package dev.burnoo.demo.listapp.data.users.network.api

import dev.burnoo.demo.listapp.data.users.network.utils.getUserResponse
import dev.burnoo.demo.listapp.data.users.network.utils.getUsersResponse
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val TEST_USER_ID = "TEST_USER_ID"
private const val TEST_FIRST_NAME = "Jon"
private const val TEST_LAST_NAME = "Doe"

class UsersApiTest {

    private val engine = MockEngine { request ->
        when (request.url.toString()) {
            "$BASE_URL/user/$TEST_USER_ID" -> respondJson(
                getUserResponse(TEST_FIRST_NAME, TEST_LAST_NAME),
            )

            "$BASE_URL/user" -> respondJson(getUsersResponse(firstUserId = TEST_USER_ID))
            else -> throw RuntimeException("Unsupported url")
        }
    }
    private val api = UsersApi(engine)

    @Test
    fun `should parse users successfully`() = runBlocking {
        val users = api.getUsers()

        users.first().id shouldBe TEST_USER_ID
    }

    @Test
    fun `should parse user successfully`() = runBlocking {
        val user = api.getUser(TEST_USER_ID)

        user.firstName shouldBe TEST_FIRST_NAME
        user.lastName shouldBe TEST_LAST_NAME
    }

    private fun MockRequestHandleScope.respondJson(json: String): HttpResponseData {
        return respond(
            content = ByteReadChannel(json),
            headers = headersOf(HttpHeaders.ContentType, "application/json"),
        )
    }
}
