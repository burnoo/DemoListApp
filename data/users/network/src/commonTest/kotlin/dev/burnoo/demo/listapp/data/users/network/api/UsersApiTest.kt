package dev.burnoo.demo.listapp.data.users.network.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.get
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.test.TestApiUser
import dev.burnoo.demo.listapp.data.users.network.test.createMockHttpEngine
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsersApiTest {

    @Test
    fun `should parse users successfully`() = runBlocking {
        val api = UsersApi(createMockHttpEngine())
        val usersResult = api.getUsers(page = 0)

        val usersResponse = usersResult.get()!!
        val firstUser = usersResponse.data.first()

        usersResponse.limit shouldBe 20
        usersResponse.page shouldBe 0
        usersResponse.total shouldBe 122
        firstUser.id shouldBe TestApiUser.id
        firstUser.title shouldBe TestApiUser.title
        firstUser.firstName shouldBe TestApiUser.firstName
        firstUser.lastName shouldBe TestApiUser.lastName
        firstUser.picture shouldBe TestApiUser.photoUrl
    }

    @Test
    fun `should handle get users client error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.NotFound)
            },
        )
        val usersResult = api.getUsers(page = 0)

        usersResult shouldBe Err(NetworkError.Client)
    }

    @Test
    fun `should handle get users server error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.InternalServerError)
            },
        )
        val usersResult = api.getUsers(page = 0)

        usersResult shouldBe Err(NetworkError.Server)
    }

    @Test
    fun `should handle get users device error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                throw RuntimeException()
            },
        )
        val usersResult = api.getUsers(page = 0)

        usersResult shouldBe Err(NetworkError.Device)
    }

    @Test
    fun `should parse user successfully`() = runBlocking {
        val api = UsersApi(createMockHttpEngine())
        val user = api.getUser(TestApiUser.id).get()!!

        user.title shouldBe TestApiUser.title
        user.firstName shouldBe TestApiUser.firstName
        user.lastName shouldBe TestApiUser.lastName
        user.gender shouldBe TestApiUser.gender
        user.email shouldBe TestApiUser.email
        user.phone shouldBe TestApiUser.phone
    }

    @Test
    fun `should handle get user client error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.NotFound)
            },
        )
        val userResult = api.getUser(TestApiUser.id)

        userResult shouldBe Err(NetworkError.Client)
    }

    @Test
    fun `should handle get user server error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.InternalServerError)
            },
        )
        val userResult = api.getUser(TestApiUser.id)

        userResult shouldBe Err(NetworkError.Server)
    }

    @Test
    fun `should handle get user device error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                throw RuntimeException()
            },
        )
        val userResult = api.getUser(TestApiUser.id)

        userResult shouldBe Err(NetworkError.Device)
    }
}
