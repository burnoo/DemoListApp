import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.userdetails"
}

kotlin {
    android()
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:designsystem"))
                implementation(project(":core:ui"))
                implementation(project(":core:utils"))
                implementation(project(":data:users:core"))
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(libs.kamel)
                implementation(libs.cokoin.core)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":data:users:core-test"))
                implementation(project(":core:compose-utils"))
                implementation(kotlin("test"))
                implementation(libs.kotest.assertions)
                implementation(libs.coroutines.test)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
