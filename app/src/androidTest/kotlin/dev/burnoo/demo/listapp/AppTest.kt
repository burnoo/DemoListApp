package dev.burnoo.demo.listapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.composable.AppRouter
import dev.burnoo.demo.listapp.core.compose.utils.di.coreComposeUtilsModule
import dev.burnoo.demo.listapp.core.designsystem.theme.AppTheme
import dev.burnoo.demo.listapp.data.users.network.test.TestApiUser
import dev.burnoo.demo.listapp.data.users.network.test.createMockEngine
import dev.burnoo.demo.listapp.ui.userdetails.di.uiUserDetailsModule
import dev.burnoo.demo.listapp.ui.userlist.di.uiUserListModule
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class AppTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayLoaderOnStart() {
        composeRule.setContent {
            WithTestDependencyInjection {
                AppTheme {
                    AppRouter()
                }
            }
        }
        composeRule.onNode(hasTestTag("Loader")).assertExists()
    }

    @Test
    fun shouldLoadAndDisplayUserListOnStart() {
        composeRule.setContent {
            WithTestDependencyInjection {
                AppTheme {
                    AppRouter()
                }
            }
        }
        composeRule.waitUntil {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }

        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.lastName, substring = true)).assertExists()
        // This is workaround for checking if there is 20 elements in the LazyList
        composeRule.onNode(hasScrollToIndexAction()).performScrollToIndex(19)
    }

    @Test
    fun shouldLoadAndDisplayUserDetailsAfterClickingOnUser() {
        composeRule.setContent {
            WithTestDependencyInjection {
                AppTheme {
                    AppRouter()
                }
            }
        }
        composeRule.waitUntil {
            composeRule
                .onAllNodes(hasTestTag("Loader"))
                .fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).performClick()

        composeRule.onNode(hasText(TestApiUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.lastName, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.title, substring = true, ignoreCase = true))
            .assertExists()
        composeRule.onNode(hasText(TestApiUser.gender, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.email, substring = true)).assertExists()
        composeRule.onNode(hasText(TestApiUser.phone, substring = true)).assertExists()
    }

    @Composable
    private fun WithTestDependencyInjection(content: @Composable () -> Unit) {
        Koin(
            appDeclaration = {
                modules(
                    coreComposeUtilsModule,
                    uiUserListModule,
                    uiUserDetailsModule,
                    module { single { createMockEngine() } },
                )
            },
            content = content,
        )
    }
}
