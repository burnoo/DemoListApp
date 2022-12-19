import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.ui)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(project(":core:designsystem"))
                implementation(project(":ui:userdetails"))
                implementation(project(":ui:userlist"))
                implementation(libs.cokoin.core)
            }
        }
    }
}

compose.experimental {
    web.application {}
}
