import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.userdetails"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:designsystem"))
                implementation(project(":core:ui"))
                implementation(project(":core:utils"))
                implementation(project(":data:users:core"))
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(libs.cokoin.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":data:users:coretest"))
                implementation(project(":core:compose-utils"))
                implementation(kotlin("test"))
                implementation(libs.kotest.assertions)
                implementation(libs.coroutines.test)
            }
        }

        val jvmTest by getting {
            dependencies {
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
