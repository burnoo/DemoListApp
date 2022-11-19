package dev.burnoo.template

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import dev.burnoo.template.composable.AppRouter
import dev.burnoo.template.core.designsystem.AppTheme
import org.junit.Rule
import org.junit.Test

class ComposeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayHelloText() {
        val text = "Hello @burnoo"
        composeRule.setContent {
            AppTheme {
                AppRouter()
            }
        }

        composeRule.onNode(hasText(text)).assertExists()
    }
}
