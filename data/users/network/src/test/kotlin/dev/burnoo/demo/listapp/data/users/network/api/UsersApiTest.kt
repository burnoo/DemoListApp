package dev.burnoo.demo.listapp.data.users.network.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.get
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import dev.burnoo.demo.listapp.data.users.network.test.TestApiUser
import dev.burnoo.demo.listapp.data.users.network.test.createMockEngine
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsersApiTest {

    @Test
    fun `should parse users successfully`() = runBlocking {
        val api = UsersApi(createMockEngine())
        val users = api.getUsers()

        val firstUser = users.get()!!.first()

        firstUser.id shouldBe TestApiUser.id
        firstUser.title shouldBe TestApiUser.title
        firstUser.firstName shouldBe TestApiUser.firstName
        firstUser.lastName shouldBe TestApiUser.lastName
        firstUser.picture shouldBe TestApiUser.photoUrl
    }

    @Test
    fun `should handle users client error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.NotFound)
            },
        )
        val usersResult = api.getUsers()

        usersResult shouldBe Err(NetworkError.Client)
    }

    @Test
    fun `should handle users server error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                respondError(HttpStatusCode.InternalServerError)
            },
        )
        val usersResult = api.getUsers()

        usersResult shouldBe Err(NetworkError.Server)
    }

    @Test
    fun `should handle users device error`() = runBlocking {
        val api = UsersApi(
            engine = MockEngine {
                throw RuntimeException()
            },
        )
        val usersResult = api.getUsers()

        usersResult shouldBe Err(NetworkError.Device)
    }

    @Test
    fun `should parse user successfully`() = runBlocking {
        val api = UsersApi(createMockEngine())
        val user = api.getUser(TestApiUser.id)

        user.title shouldBe TestApiUser.title
        user.firstName shouldBe TestApiUser.firstName
        user.lastName shouldBe TestApiUser.lastName
        user.gender shouldBe TestApiUser.gender
        user.email shouldBe TestApiUser.email
        user.phone shouldBe TestApiUser.phone
    }
}
