package dev.burnoo.demo.listapp.data.users.network.api

import dev.burnoo.demo.listapp.data.users.network.test.TestApiUser
import dev.burnoo.demo.listapp.data.users.network.test.createMockEngine
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsersApiTest {

    private val api = UsersApi(createMockEngine())

    @Test
    fun `should parse users successfully`() = runBlocking {
        val users = api.getUsers()

        users.first().id shouldBe TestApiUser.id
        users.first().title shouldBe TestApiUser.title
        users.first().firstName shouldBe TestApiUser.firstName
        users.first().lastName shouldBe TestApiUser.lastName
        users.first().picture shouldBe TestApiUser.photoUrl
    }

    @Test
    fun `should parse user successfully`() = runBlocking {
        val user = api.getUser(TestApiUser.id)

        user.title shouldBe TestApiUser.title
        user.firstName shouldBe TestApiUser.firstName
        user.lastName shouldBe TestApiUser.lastName
        user.gender shouldBe TestApiUser.gender
        user.email shouldBe TestApiUser.email
        user.phone shouldBe TestApiUser.phone
    }
}
