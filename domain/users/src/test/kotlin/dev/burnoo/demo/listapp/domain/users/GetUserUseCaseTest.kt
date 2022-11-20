package dev.burnoo.demo.listapp.domain.users

import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testUser
import dev.burnoo.demo.listapp.data.users.core.testUsers
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test

// This test could be removed as it doesn't test any logic.
// It is here just to let you know that I care about testing UseCases ;)
class GetUserUseCaseTest {

    private val repository = FakeUsersRepository()
    private val getUserUseCase = GetUserUseCase(repository)

    @Test
    fun `should get users list from repository`() = runBlocking {
        val user = getUserUseCase(testUsers.first().id)

        user shouldBe testUser
    }
}
