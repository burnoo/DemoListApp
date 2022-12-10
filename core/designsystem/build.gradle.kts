import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.theme"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kamel)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
            }
        }
    }
}
