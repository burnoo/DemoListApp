package dev.burnoo.demo.listapp.ui.userdetails

import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testUser
import dev.burnoo.demo.listapp.data.users.core.testUsers
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository = FakeUsersRepository()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should ui state be loading initially`() {
        val viewModel = UserDetailsViewModel(repository, userId = testUsers.first().id)

        viewModel.uiState.value shouldBe UserDetailsUiState.Loading
    }

    @Test
    fun `should ui state contain loaded user`() {
        val viewModel = UserDetailsViewModel(repository, userId = testUsers.first().id)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Loaded(testUser)
    }
}
