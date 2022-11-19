package dev.burnoo.demo.listapp

import androidx.compose.material3.Text
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ComposeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayHelloText() {
        val text = "Hello @burnoo"
        composeRule.setContent {
            Text(text)
        }

        composeRule.onNode(hasText(text)).assertExists()
    }
}
