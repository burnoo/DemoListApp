package dev.burnoo.demo.listapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.getKoin
import dev.burnoo.demo.listapp.core.compose.utils.di.coreComposeUtilsModule
import dev.burnoo.demo.listapp.data.users.network.test.TestApiUser
import dev.burnoo.demo.listapp.data.users.network.test.createMockHttpEngine
import dev.burnoo.demo.listapp.ui.userdetails.di.uiUserDetailsModule
import dev.burnoo.demo.listapp.ui.userlist.di.uiUserListModule
import io.ktor.client.engine.HttpClientEngine
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class AndroidAppTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayLoaderOnStart() {
        composeRule.setContent {
            WithTestDependencyInjection { App() }
        }
        composeRule.onNode(hasTestTag("Loader")).assertExists()
    }

    @Test
    fun shouldLoadAndDisplayUserListOnStart() {
        composeRule.setContent {
            WithTestDependencyInjection { App() }
        }
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }

        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.lastName, substring = true)).assertExists()
        // This is workaround for checking if there is 21 elements in the LazyList
        composeRule.onNode(hasScrollToIndexAction()).performScrollToIndex(20)
    }

    @Test
    fun shouldLoadAndDisplayUserDetailsAfterClickingOnUser() {
        composeRule.setContent {
            WithTestDependencyInjection { App() }
        }
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).performClick()

        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.lastName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.title, substring = true, ignoreCase = true))
            .assertExists()
        composeRule.onNode(hasText(TestApiUser.gender, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.email, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.phone, substring = true)).assertExists()
    }

    @Test
    fun shouldDisplayTryAgainButtonOnNetworkErrorWhenGettingUserList() {
        val mockEngine = createMockHttpEngine(
            getUsers = { throw RuntimeException() },
        )
        composeRule.setContent {
            WithTestDependencyInjection(mockEngine) { App() }
        }
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasTestTag("Try again button")).assertExists()
    }

    @Test
    fun shouldDisplayTryAgainButtonOnNetworkErrorWhenGettingUserDetails() {
        val mockEngine = createMockHttpEngine(
            getUser = { throw RuntimeException() },
        )
        composeRule.setContent {
            WithTestDependencyInjection(httpClientEngine = mockEngine) { App() }
        }
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).performClick()
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasTestTag("Try again button")).assertExists()
    }

    @Composable
    private fun WithTestDependencyInjection(
        httpClientEngine: HttpClientEngine = createMockHttpEngine(),
        content: @Composable () -> Unit,
    ) {
        Koin(
            appDeclaration = {
                modules(
                    coreComposeUtilsModule,
                    uiUserListModule,
                    uiUserDetailsModule,
                    module { factory { httpClientEngine } },
                )
            },
        ) {
            content()

            // This should be included in cokoin library, I will fix it
            val koin = getKoin()
            DisposableEffect(key1 = Unit) {
                onDispose { koin.close() }
            }
        }
    }
}
