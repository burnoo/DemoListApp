package dev.burnoo.demo.listapp.core.data

import app.cash.turbine.test
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

private object TestError

private class TestFetcher {
    var isResponsesSuccess = listOf(true)
    var lastPage = Int.MAX_VALUE

    fun fetch(page: Int): Result<Pager.PagedList<Int>, TestError> {
        return if (isResponsesSuccess[page % isResponsesSuccess.size]) {
            Ok(Pager.PagedList(listOf(page * -1, page), isLastPage = page >= lastPage))
        } else {
            Err(TestError)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class PagerTest {
    private val testFetcher = TestFetcher()
    private val pager = Pager(fetchPagedList = testFetcher::fetch)

    @Test
    fun shouldLoadFirstPage() = runTest {
        pager.status.test {
            pager.loadPage()
            val status = awaitItem()

            status.fullList shouldBe listOf(0, 0)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Ok(listOf(0, 0))
        }
    }

    @Test
    fun shouldLoadTwoPages() = runTest {
        pager.status.test {
            pager.loadPage()
            skipItems(1)
            pager.loadPage()
            val status = awaitItem()

            status.fullList shouldBe listOf(0, 0, -1, 1)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Ok(listOf(-1, 1))
        }
    }

    @Test
    fun shouldLoadFirstPageAndFailOnSecond() = runTest {
        testFetcher.isResponsesSuccess = listOf(true, false)
        pager.status.test {
            pager.loadPage()
            skipItems(1)
            pager.loadPage()
            val status = awaitItem()

            status.fullList shouldBe listOf(0, 0)
            status.isLastPage shouldBe false
            status.lastResult shouldBe Err(TestError)
        }
    }

    @Test
    fun shouldContainInfoAboutLastPage() = runTest {
        testFetcher.lastPage = 0
        pager.status.test {
            pager.loadPage()
            val status = awaitItem()

            status.fullList shouldBe listOf(0, 0)
            status.isLastPage shouldBe true
            status.lastResult shouldBe Ok(listOf(0, 0))
        }
    }

    @Test
    fun shouldQueueLoadPageRequests() = runTest {
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
            firstStatus.fullList shouldBe listOf(0, 0)
            firstStatus.isLastPage shouldBe false
            firstStatus.lastResult shouldBe Ok(listOf(0, 0))

            testDispatcher.scheduler.advanceTimeBy(1000L)

            val secondStatus = awaitItem()
            secondStatus.fullList shouldBe listOf(0, 0, -1, 1)
            secondStatus.isLastPage shouldBe false
            secondStatus.lastResult shouldBe Ok(listOf(-1, 1))
        }
    }
}
