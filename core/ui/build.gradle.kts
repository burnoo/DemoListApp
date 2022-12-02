plugins {
    kotlin("multiplatform")
    id("common-android-library")
    id("org.jetbrains.compose")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    namespace = "dev.burnoo.demo.listapp.core.ui"
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koin)
                implementation(libs.coroutines.core)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.cokoin.viewmodel)
            }
        }
    }
}
