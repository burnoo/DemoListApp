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
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koin)
                api(compose.runtime)
                implementation(libs.coroutines.core)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.cokoin.viewmodel)
                implementation(libs.androidx.lifecycle.compose)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.cokoin.core)
            }
        }
    }
}
