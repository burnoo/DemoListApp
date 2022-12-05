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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsersRemoteRepositoryTest {

    private val fakeDataSource = FakeUsersNetworkDataSource()
    private val repository = UsersRemoteRepository(
        dataSource = fakeDataSource,
        coroutineDispatcher = Dispatchers.Unconfined,
    )

    @Test
    fun `should get user list successfully`() = runBlocking {
        val pager = repository.getUserListPager()
        pager.status.test {
            pager.loadPage()
            val users = awaitItem().currentList

            users.forEachIndexed { index, userItem ->
                userItem.id.value shouldBe testNetworkUsers[index].id
                userItem.firstName shouldBe testNetworkUsers[index].firstName
                userItem.lastName shouldBe testNetworkUsers[index].lastName
                userItem.title shouldBe testNetworkUsers[index].title
                userItem.photoUrl shouldBe testNetworkUsers[index].picture
            }
        }
    }

    @Test
    fun `should handle get user list errors`() = runBlocking {
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
    fun `should get user successfully`() = runBlocking {
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
    fun `should handle get user errors`() = runBlocking {
        val userId = UserId(value = "TEST_USER_ID")
        val networkErrors = listOf(NetworkError.Client, NetworkError.Server, NetworkError.Device)
        networkErrors.forEach { networkError ->
            fakeDataSource.userResult = Err(networkError)
            val usersResult = repository.getUser(userId)

            usersResult shouldBe Err(DataError)
        }
    }

    @Test
    fun `should calculate if is on the last page`() = runBlocking {
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
    fun `should calculate if is not on the last page`() = runBlocking {
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
