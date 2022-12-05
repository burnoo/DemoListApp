@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("multiplatform")
    id("common-android-library")
    id("org.jetbrains.compose")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
        languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
    }

    jvm()
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
}
