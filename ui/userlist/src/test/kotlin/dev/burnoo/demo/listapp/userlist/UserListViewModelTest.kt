package dev.burnoo.demo.listapp.userlist

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testUsers
import dev.burnoo.demo.listapp.data.users.model.DataError
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
        val viewModel = UserListViewModel(repository)

        viewModel.uiState.value shouldBe UserListUiState.Loading
    }

    @Test
    fun `should ui state contain loaded users on successful first page load`() {
        val viewModel = UserListViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(testUsers, isLastPage = false)
    }

    @Test
    fun `should ui state be error on data error on first page`() {
        repository.setUsersResults(Err(DataError))
        val viewModel = UserListViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Error
    }

    @Test
    fun `should ui state contain loaded users after trying again`() {
        repository.setUsersResults(Err(DataError), Ok(testUsers))
        val viewModel = UserListViewModel(repository)

        viewModel.tryAgain()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(testUsers, isLastPage = false)
    }

    @Test
    fun `should ui state contain users from two first pages`() {
        repository.setUsersResults(Ok(testUsers), Ok(testUsers.reversed()))
        val viewModel = UserListViewModel(repository)

        viewModel.fetchNextPage()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(
            users = testUsers + testUsers.reversed(),
            isLastPage = false,
        )
    }
}
