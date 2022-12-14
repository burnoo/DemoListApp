package dev.burnoo.demo.listapp.ui.userlist.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performScrollToNode
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.core.compose.utils.di.coreComposeUtilsModule
import dev.burnoo.demo.listapp.data.users.core.testUserList
import dev.burnoo.demo.listapp.ui.userlist.UserListUiState
import dev.burnoo.demo.listapp.ui.userlist.di.uiUserListModule
import io.kotest.assertions.throwables.shouldThrow
import org.junit.Rule
import org.junit.Test

class UserListContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `should display loader when loading`() {
        composeRule.setContent {
            UserListContent(
                UserListUiState.Loading,
                onUserClick = {},
                onTryAgain = {},
                onLoadMore = {},
            )
        }

        composeRule.onNode(hasTestTag("Loader")).assertExists()
    }

    @Test
    fun `should display users when loaded`() {
        composeRule.setContent {
            WithTestDependencyInjection {
                UserListContent(
                    UserListUiState.Loaded(testUserList, isLastPage = false),
                    onUserClick = {},
                    onTryAgain = {},
                    onLoadMore = {},
                )
            }
        }

        testUserList.forEach { user ->
            composeRule.onNode(hasText(user.firstName, substring = true)).assertExists()
            composeRule.onNode(hasText(user.lastName, substring = true)).assertExists()
        }
    }

    @Test
    fun `should display more loader if not on the last page`() {
        composeRule.setContent {
            WithTestDependencyInjection {
                UserListContent(
                    UserListUiState.Loaded(testUserList, isLastPage = false),
                    onUserClick = {},
                    onTryAgain = {},
                    onLoadMore = {},
                )
            }
        }

        composeRule.onNode(hasScrollAction()).performScrollToNode(hasTestTag("More loader"))
        composeRule.onNode(hasTestTag("More loader")).assertExists()
    }

    @Test
    fun `should not display more loader if on the last page`() {
        composeRule.setContent {
            WithTestDependencyInjection {
                UserListContent(
                    UserListUiState.Loaded(testUserList, isLastPage = true),
                    onUserClick = {},
                    onTryAgain = {},
                    onLoadMore = {},
                )
            }
        }

        shouldThrow<AssertionError> {
            composeRule.onNode(hasScrollAction()).performScrollToNode(hasTestTag("More loader"))
        }
    }

    @Test
    fun `should display try again button on error`() {
        composeRule.setContent {
            UserListContent(
                UserListUiState.Error,
                onUserClick = {},
                onTryAgain = {},
                onLoadMore = {},
            )
        }

        composeRule.onNode(hasTestTag("Try again button")).assertExists()
        composeRule.onNode(hasTestTag("Try again button")).assertHasClickAction()
    }

    @Composable
    private fun WithTestDependencyInjection(content: @Composable () -> Unit) {
        Koin(
            appDeclaration = { modules(coreComposeUtilsModule, uiUserListModule) },
            content = content,
        )
    }
}
