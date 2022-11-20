package dev.burnoo.demo.listapp.ui.userdetails.composables

import androidx.compose.runtime.Composable
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
    fun shouldDisplayLoaderWhenLoading() {
        composeRule.setContent {
            UserDetailsContent(uiState = UserDetailsUiState.Loading)
        }

        composeRule.onNode(hasTestTag("Loader")).assertExists()
    }

    @Test
    fun shouldDisplayUserDetailsWhenLoaded() {
        composeRule.setContent {
            WithTestDependencyInjection {
                UserDetailsContent(uiState = UserDetailsUiState.Loaded(testUser))
            }
        }

        composeRule.onNode(hasText(testUser.firstName, substring = true)).assertExists()
        composeRule.onNode(hasText(testUser.lastName, substring = true)).assertExists()
        composeRule.onNode(hasText(testUser.title, substring = true, ignoreCase = true))
            .assertExists()
        composeRule.onNode(hasText(testUser.gender)).assertExists()
        composeRule.onNode(hasText(testUser.phone)).assertExists()
        composeRule.onNode(hasText(testUser.email)).assertExists()
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
