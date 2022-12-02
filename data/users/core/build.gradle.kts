plugins {
    id("common-kotlin-multiplatform")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":data:users:model"))
            api(project(":core:data"))
            api(libs.kotlinResult)
            implementation(project(":data:users:network"))
            implementation(libs.coroutines.core)
            implementation(libs.koin)
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotest.assertions)
            implementation(libs.turbine)
        }
    }
}
