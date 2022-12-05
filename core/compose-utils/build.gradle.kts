plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.core.compose.utils"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:designsystem"))
                implementation(libs.kamel)
                implementation(libs.koin)
            }
        }
    }
}
