@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        applicationId = "dev.burnoo.demo.listapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompiler.get()
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}

configurations.all {
    resolutionStrategy {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
}

dependencies {

    implementation(project(":core:designsystem"))
    implementation(project(":ui:userlist"))
    implementation(libs.jetpackCompose.material3)
    implementation(libs.jetpackCompose.uiTooling.preview)
    implementation(libs.jetpackCompose.navigation)
    implementation(libs.activity.compose)
    implementation(libs.cokoin.viewmodel)
    debugImplementation(libs.jetpackCompose.uiTooling)

    compileOnly(libs.kotlinx.serialization.json)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.assertJ)

    debugImplementation(libs.jetpackCompose.uiTest.manifest)
    androidTestImplementation(libs.jetpackCompose.uiTest.junit)
    androidTestImplementation(libs.test.androidCore)
}
