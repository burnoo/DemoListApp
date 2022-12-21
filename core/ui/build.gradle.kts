import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.core.ui"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koin)
                api(compose.runtime)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                api(libs.coroutines.core)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.cokoin.core)
            }
        }

        val jvmCommonMain by creating {
            dependsOn(commonMain)
        }

        val androidMain by getting {
            dependsOn(jvmCommonMain)
            dependencies {
                api(libs.cokoin.viewmodel)
                implementation(libs.androidx.lifecycle.compose)
            }
        }

        val jvmMain by getting {
            dependsOn(nonAndroidMain)
            dependsOn(jvmCommonMain)
        }

        val jsMain by getting {
            dependsOn(nonAndroidMain)
        }
    }
}
