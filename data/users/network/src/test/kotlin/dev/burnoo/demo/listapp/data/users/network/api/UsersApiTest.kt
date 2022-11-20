package dev.burnoo.demo.listapp.data.users.network.api

import dev.burnoo.demo.listapp.data.users.network.test.TEST_FIRST_NAME
import dev.burnoo.demo.listapp.data.users.network.test.TEST_LAST_NAME
import dev.burnoo.demo.listapp.data.users.network.test.TEST_USER_ID
import dev.burnoo.demo.listapp.data.users.network.test.createMockEngine
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsersApiTest {

    private val api = UsersApi(createMockEngine())

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
}
