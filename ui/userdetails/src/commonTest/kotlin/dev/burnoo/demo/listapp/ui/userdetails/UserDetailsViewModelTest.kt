package dev.burnoo.demo.listapp.ui.userdetails

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testPagedUserList
import dev.burnoo.demo.listapp.data.users.core.testUser
import dev.burnoo.demo.listapp.data.users.core.testUserList
import dev.burnoo.demo.listapp.data.users.model.DataError
import io.kotest.matchers.shouldBe
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository = FakeUsersRepository()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldUiStateBeLoadingInitially() {
        val viewModel = UserDetailsViewModel(repository, userId = testUserList.first().id)

        viewModel.uiState.value shouldBe UserDetailsUiState.Loading
    }

    @Test
    fun shouldUiStateContainLoadedUserOnSuccessfulDataLoad() {
        val viewModel = UserDetailsViewModel(repository, userId = testUserList.first().id)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Loaded(testUser)
    }

    @Test
    fun shouldUiStateBeErrorOnDataError() {
        repository.setUserResults(Err(DataError))
        val viewModel = UserDetailsViewModel(repository, testUserList.first().id)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Error
    }

    @Test
    fun shouldUiStateContainLoadedUserAfterTryingAgain() {
        repository.setUsersResults(Err(DataError), Ok(testPagedUserList()))
        val viewModel = UserDetailsViewModel(repository, testUserList.first().id)

        viewModel.tryAgain()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserDetailsUiState.Loaded(testUser)
    }
}
