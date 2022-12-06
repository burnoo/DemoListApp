package dev.burnoo.demo.listapp.core.utils

import io.kotest.matchers.shouldBe
import java.util.Locale
import org.junit.Test

class TitleParserTest {

    private val titleParser = TitleParser(locale = Locale.US)

    @Test
    fun `should parse mr as Mr with dot`() {
        val parsedTitle = titleParser.parse("mr")

        parsedTitle shouldBe "Mr."
    }

    @Test
    fun `should parse ms as Ms with dot`() {
        val parsedTitle = titleParser.parse("ms")

        parsedTitle shouldBe "Ms."
    }

    @Test
    fun `should parse mrs as Mrs with dot`() {
        val parsedTitle = titleParser.parse("mrs")

        parsedTitle shouldBe "Mrs."
    }

    @Test
    fun `should parse miss as Miss`() {
        val parsedTitle = titleParser.parse("miss")

        parsedTitle shouldBe "Miss"
    }

    @Test
    fun `should ignore initial capitalization`() {
        val parsedTitle = titleParser.parse("mIsS")

        parsedTitle shouldBe "Miss"
    }
}
