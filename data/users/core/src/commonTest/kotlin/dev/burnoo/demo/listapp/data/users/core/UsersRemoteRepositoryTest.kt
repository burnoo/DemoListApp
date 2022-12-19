package dev.burnoo.demo.listapp.data.users.core

import app.cash.turbine.test
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import dev.burnoo.demo.listapp.data.users.core.utils.FakeUsersNetworkDataSource
import dev.burnoo.demo.listapp.data.users.core.utils.testNetworkResponse
import dev.burnoo.demo.listapp.data.users.core.utils.testNetworkUser
import dev.burnoo.demo.listapp.data.users.core.utils.testNetworkUsers
import dev.burnoo.demo.listapp.data.users.model.DataError
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.network.model.NetworkError
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class UsersRemoteRepositoryTest {

    private val fakeDataSource = FakeUsersNetworkDataSource()
    private val repository = UsersRemoteRepository(
        dataSource = fakeDataSource,
        coroutineDispatcher = Dispatchers.Unconfined,
    )

    @Test
    fun shouldGetUserListSuccessfully() = runTest {
        val pager = repository.getUserListPager()
        pager.status.test {
            pager.loadPage()
            val users = awaitItem().fullList

            users.zip(testNetworkUsers) { userItem, networkUser ->
                userItem.id.value shouldBe networkUser.id
                userItem.firstName shouldBe networkUser.firstName
                userItem.lastName shouldBe networkUser.lastName
                userItem.title shouldBe networkUser.title
                userItem.photoUrl shouldBe networkUser.picture
            }
        }
    }

    @Test
    fun shouldHandleGetUserListErrors() = runTest {
        val networkErrors = listOf(NetworkError.Client, NetworkError.Server, NetworkError.Device)
        networkErrors.forEach { networkError ->
            fakeDataSource.usersResult = Err(networkError)
            val pager = repository.getUserListPager()
            pager.status.test {
                pager.loadPage()
                val usersResult = awaitItem().lastResult

                usersResult shouldBe Err(DataError)
            }
        }
    }

    @Test
    fun shouldGetUserSuccessfully() = runTest {
        val userId = UserId(value = "TEST_USER_ID")
        val user = repository.getUser(userId).get()!!

        user.title shouldBe testNetworkUser.title
        user.firstName shouldBe testNetworkUser.firstName
        user.lastName shouldBe testNetworkUser.lastName
        user.photoUrl shouldBe testNetworkUser.picture
        user.gender shouldBe testNetworkUser.gender
        user.email shouldBe testNetworkUser.email
        user.phone shouldBe testNetworkUser.phone
    }

    @Test
    fun shouldHandleGetUserErrors() = runTest {
        val userId = UserId(value = "TEST_USER_ID")
        val networkErrors = listOf(NetworkError.Client, NetworkError.Server, NetworkError.Device)
        networkErrors.forEach { networkError ->
            fakeDataSource.userResult = Err(networkError)
            val usersResult = repository.getUser(userId)

            usersResult shouldBe Err(DataError)
        }
    }

    @Test
    fun shouldCalculateIfIsOnTheLastPage() = runTest {
        fakeDataSource.usersResult = Ok(
            testNetworkResponse(
                total = 122,
                page = 6,
                limit = 20,
            ),
        )

        val pager = repository.getUserListPager()
        pager.status.test {
            pager.loadPage()
            val isLastPage = awaitItem().isLastPage

            isLastPage shouldBe true
        }
    }

    @Test
    fun shouldCalculateIfIsNotOnTheLastPage() = runTest {
        fakeDataSource.usersResult = Ok(
            testNetworkResponse(
                total = 122,
                page = 5,
                limit = 20,
            ),
        )

        val pager = repository.getUserListPager()
        pager.status.test {
            pager.loadPage()
            val isLastPage = awaitItem().isLastPage

            isLastPage shouldBe false
        }
    }
}
