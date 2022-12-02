@file:Suppress("UnstableApiUsage")

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("common-android-library")
    id("org.jetbrains.compose")
}

val libs = the<LibrariesForLibs>()

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}
