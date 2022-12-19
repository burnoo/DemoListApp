package dev.burnoo.demo.listapp.core.utils

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class TitleParserTest {

    private val titleParser = TitleParser()

    @Test
    fun shouldParseMrAsMrWithDot() {
        val parsedTitle = titleParser.parse("mr")

        parsedTitle shouldBe "Mr."
    }

    @Test
    fun shouldParseMsAsMsWithDot() {
        val parsedTitle = titleParser.parse("ms")

        parsedTitle shouldBe "Ms."
    }

    @Test
    fun shouldParseMrsAsMrsWithDot() {
        val parsedTitle = titleParser.parse("mrs")

        parsedTitle shouldBe "Mrs."
    }

    @Test
    fun shouldParseMissAsMiss() {
        val parsedTitle = titleParser.parse("miss")

        parsedTitle shouldBe "Miss"
    }

    @Test
    fun shouldIgnoreInitialCapitalization() {
        val parsedTitle = titleParser.parse("mIsS")

        parsedTitle shouldBe "Miss"
    }
}
