@file:Suppress("UnstableApiUsage")

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("common-android-library")
}

val libs = the<LibrariesForLibs>()

android {
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompiler.get()
    }

    buildFeatures {
        compose = true
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}
