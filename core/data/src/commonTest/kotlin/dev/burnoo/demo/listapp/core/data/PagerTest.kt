package dev.burnoo.demo.listapp.core.data

import app.cash.turbine.test
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Test

private object TestError

private class TestFetcher {
    var isResponsesSuccess = listOf(true)
    var lastPage = Integer.MAX_VALUE

    fun fetch(page: Int): Result<Pager.PagedList<Int>, TestError> {
        return if (isResponsesSuccess[page % isResponsesSuccess.size]) {
            Ok(Pager.PagedList(listOf(page * -1, page), isLastPage = page >= lastPage))
        } else {
            Err(TestError)
        }
    }
}

class PagerTest {
    private val testFetcher = TestFetcher()
    private val pager = Pager(fetchPagedList = testFetcher::fetch)

    @Test
    fun `should load first page`() = runBlocking {
        pager.status.test {
            pager.loadPage()
            val status = awaitItem()

            status.currentList shouldBe listOf(0, 0)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Ok(Unit)
        }
    }

    @Test
    fun `should load two pages`() = runBlocking {
        pager.status.test {
            pager.loadPage()
            skipItems(1)
            pager.loadPage()
            val status = awaitItem()

            status.currentList shouldBe listOf(0, 0, -1, 1)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Ok(Unit)
        }
    }

    @Test
    fun `should load first page and fail on second`() = runBlocking {
        testFetcher.isResponsesSuccess = listOf(true, false)
        pager.status.test {
            pager.loadPage()
            skipItems(1)
            pager.loadPage()
            val status = awaitItem()

            status.currentList shouldBe listOf(0, 0)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Err(TestError)
        }
    }

    @Test
    fun `should contain info about last page`() = runBlocking {
        testFetcher.lastPage = 0
        pager.status.test {
            pager.loadPage()
            val status = awaitItem()

            status.currentList shouldBe listOf(0, 0)
            status.isLastPage shouldBe true
            status.lastResult shouldBe Ok(Unit)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should queue load page requests`() = runBlocking {
        val testDispatcher = StandardTestDispatcher()
        val pager = Pager(
            fetchPagedList = { page ->
                delay(1000L)
                Ok(Pager.PagedList(listOf(page * -1, page), isLastPage = false))
            },
        )
        CoroutineScope(testDispatcher).launch { pager.loadPage() }
        CoroutineScope(testDispatcher).launch { pager.loadPage() }
        testDispatcher.scheduler.advanceTimeBy(10L)
        pager.status.test {
            testDispatcher.scheduler.advanceTimeBy(1000L)

            val firstStatus = awaitItem()
            firstStatus.currentList shouldBe listOf(0, 0)
            firstStatus.isLastPage shouldBe false
            firstStatus.lastResult shouldBe Ok(Unit)

            testDispatcher.scheduler.advanceTimeBy(1000L)

            val secondStatus = awaitItem()
            secondStatus.currentList shouldBe listOf(0, 0, -1, 1)
            secondStatus.isLastPage shouldBe false
            secondStatus.lastResult shouldBe Ok(Unit)
        }
    }
}
