package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import dev.burnoo.cokoin.Koin
import dev.burnoo.demo.listapp.core.compose.utils.di.coreComposeUtilsModule
import dev.burnoo.demo.listapp.data.users.core.testUser
import dev.burnoo.demo.listapp.ui.userdetails.UserDetailsUiState
import dev.burnoo.demo.listapp.ui.userdetails.di.uiUserDetailsModule
import org.junit.Rule
import org.junit.Test

class UserDetailsContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `should display loader when loading`() {
        composeRule.setContent {
            UserDetailsContent(uiState = UserDetailsUiState.Loading, onTryAgain = {}, onGoBack = {})
        }

        composeRule.onNode(hasTestTag("Loader")).assertExists()
    }

    @Test
    fun `should display try again and go back buttons on error`() {
        composeRule.setContent {
            UserDetailsContent(UserDetailsUiState.Error, onTryAgain = {}, onGoBack = {})
        }

        composeRule.onNode(hasTestTag("Try again button")).apply {
            assertExists()
            assertHasClickAction()
        }
        composeRule.onNode(hasTestTag("Go back button")).apply {
            assertExists()
            assertHasClickAction()
        }
    }

    @Test
    fun `should display user details when loaded`() {
        composeRule.setContent {
            WithTestDependencyInjection {
                UserDetailsContent(uiState = UserDetailsUiState.Loaded(testUser), onTryAgain = {}, onGoBack = {})
            }
        }

        composeRule.onNode(hasText(testUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(testUser.lastName, substring = true)).assertExists()
        composeRule.onNode(hasText(testUser.title, substring = true, ignoreCase = true))
            .assertExists()
        composeRule.onNode(hasText(testUser.gender)).assertExists()
        composeRule.onNode(hasText(testUser.phone)).assertExists()
        composeRule.onNode(hasText(testUser.email)).assertExists()
        composeRule.onNode(hasTestTag("Go back button")).apply {
            assertExists()
            assertHasClickAction()
        }
        composeRule.onNode(hasTestTag("Loader")).assertDoesNotExist()
    }

    @Composable
    private fun WithTestDependencyInjection(content: @Composable () -> Unit) {
        Koin(
            appDeclaration = { modules(coreComposeUtilsModule, uiUserDetailsModule) },
            content = content,
        )
    }
}
