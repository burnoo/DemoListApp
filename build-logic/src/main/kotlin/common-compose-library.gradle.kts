@file:Suppress("UnstableApiUsage")

plugins {
    id("common-android-library")
    id("org.jetbrains.compose")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}
