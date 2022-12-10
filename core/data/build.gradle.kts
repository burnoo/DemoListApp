plugins {
    id("common-kotlin-multiplatform")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(libs.coroutines.core)
            api(libs.kotlinResult)
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotest.assertions)
            implementation(libs.turbine)
            implementation(libs.coroutines.test)
        }
    }
}
