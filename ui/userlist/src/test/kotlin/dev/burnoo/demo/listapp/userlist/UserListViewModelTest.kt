package dev.burnoo.demo.listapp.userlist

import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testUsers
import dev.burnoo.demo.listapp.domain.users.GetUsersUseCase
import dev.burnoo.demo.listapp.ui.userlist.UserListUiState
import dev.burnoo.demo.listapp.ui.userlist.UserListViewModel
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
class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository = FakeUsersRepository()
    private val getUsersUseCase = GetUsersUseCase(repository)

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
        val viewModel = UserListViewModel(getUsersUseCase)

        viewModel.uiState.value shouldBe UserListUiState.Loading
    }

    @Test
    fun `should ui state contains loaded users`() {
        val viewModel = UserListViewModel(getUsersUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(testUsers)
    }
}
