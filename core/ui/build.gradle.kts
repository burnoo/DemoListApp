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
                api(libs.coroutines.core)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.cokoin.core)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.cokoin.viewmodel)
                implementation(libs.androidx.lifecycle.compose)
            }
        }

        val jvmMain by getting {
            dependsOn(nonAndroidMain)
        }

        val jsMain by getting {
            dependsOn(nonAndroidMain)
        }
    }
}
