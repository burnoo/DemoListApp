package dev.burnoo.demo.listapp.core.utils

class TitleParser {

    fun parse(title: String): String {
        val lowercaseTitle = title.lowercase()
        return when (lowercaseTitle) {
            "mr", "mrs", "ms" -> "$lowercaseTitle."
            else -> lowercaseTitle
        }.replaceFirstChar { it.titlecase() }
    }
}
