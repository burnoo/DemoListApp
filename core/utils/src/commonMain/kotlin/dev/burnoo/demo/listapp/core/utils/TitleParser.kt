package dev.burnoo.demo.listapp.core.utils

import java.util.*

class TitleParser(private val locale: Locale = Locale.getDefault()) {

    fun parse(title: String): String {
        val lowercaseTitle = title.lowercase(locale)
        return when (lowercaseTitle) {
            "mr", "mrs", "ms" -> "$lowercaseTitle."
            else -> lowercaseTitle
        }.replaceFirstChar { it.titlecase(locale) }
    }
}
