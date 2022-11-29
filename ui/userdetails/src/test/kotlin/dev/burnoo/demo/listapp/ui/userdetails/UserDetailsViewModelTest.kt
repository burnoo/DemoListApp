package dev.burnoo.demo.listapp.ui.userdetails

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testUser
import dev.burnoo.demo.listapp.data.users.core.testUsers
import dev.burnoo.demo.listapp.data.users.model.DataError
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
    fun `should ui state contain loaded user on successful data load`() {
        val viewModel = UserDetailsViewModel(repository, userId = testUsers.first().id)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Loaded(testUser)
    }

    @Test
    fun `should ui state be error on data error`() {
        repository.setUserResults(Err(DataError))
        val viewModel = UserDetailsViewModel(repository, testUsers.first().id)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Error
    }

    @Test
    fun `should ui state contain loaded user after trying again`() {
        repository.setUsersResults(Err(DataError), Ok(testUsers))
        val viewModel = UserDetailsViewModel(repository, testUsers.first().id)

        viewModel.tryAgain()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Loaded(testUser)
    }
}
