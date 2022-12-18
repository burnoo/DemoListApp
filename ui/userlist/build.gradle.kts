import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("common-compose-library")
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlinx.atomicFu)
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.userlist"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:designsystem"))
                implementation(project(":core:ui"))
                implementation(project(":core:utils"))
                implementation(project(":data:users:core"))
                api(project(":data:users:model"))
                implementation(libs.kotlinResult)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(libs.cokoin.core)
                implementation(libs.kotlinx.atomicFu)
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

        all {
            languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
        }
    }
}
