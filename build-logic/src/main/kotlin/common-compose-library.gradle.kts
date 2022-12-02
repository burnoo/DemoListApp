@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("android")
    id("common-android-library")
    id("org.jetbrains.compose")
}

android {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}
