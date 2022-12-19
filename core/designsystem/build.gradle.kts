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
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
            }
        }

        val jvmCommon by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.kamel)
            }
        }

        val jvmMain by getting {
            dependsOn(jvmCommon)
        }

        val androidMain by getting {
            dependsOn(jvmCommon)
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(libs.imageLoader)
            }
        }
    }
}
