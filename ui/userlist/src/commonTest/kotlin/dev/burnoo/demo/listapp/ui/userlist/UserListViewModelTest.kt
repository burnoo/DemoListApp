package dev.burnoo.demo.listapp.ui.userlist

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.burnoo.demo.listapp.data.users.core.FakeUsersRepository
import dev.burnoo.demo.listapp.data.users.core.testPagedUserList
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
class UserListViewModelTest {

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
        val viewModel = UserListViewModel(repository)

        viewModel.uiState.value shouldBe UserListUiState.Loading
    }

    @Test
    fun shouldUiStateContainLoadedUsersOnSuccessfulFirstPageLoad() {
        val viewModel = UserListViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(testUserList, isLastPage = false)
    }

    @Test
    fun shouldUiStateBeErrorOnDataErrorOnFirstPage() {
        repository.setUsersResults(Err(DataError))
        val viewModel = UserListViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Error
    }

    @Test
    fun shouldUiStateContainLoadedUsersAfterTryingAgain() {
        repository.setUsersResults(Err(DataError), Ok(testPagedUserList()))
        val viewModel = UserListViewModel(repository)

        viewModel.tryAgain()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(testUserList, isLastPage = false)
    }

    @Test
    fun shouldUiStateContainUsersFromTwoFirstPages() {
        repository.setUsersResults(
            Ok(testPagedUserList()),
            Ok(testPagedUserList(list = testUserList.reversed())),
        )
        val viewModel = UserListViewModel(repository)

        viewModel.fetchNextPage()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(
            users = testUserList + testUserList.reversed(),
            isLastPage = false,
        )
    }

    @Test
    fun shouldNotFetchNextPageIfAlreadyLoadingOne() {
        repository.delayMillis = 1000L
        val viewModel = UserListViewModel(repository)

        viewModel.fetchNextPage()
        testDispatcher.scheduler.advanceTimeBy(10L)
        viewModel.fetchNextPage()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.value shouldBe UserListUiState.Loaded(
            users = testUserList + testUserList,
            isLastPage = false,
        )
    }
}
