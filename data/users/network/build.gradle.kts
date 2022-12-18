plugins {
    id("common-kotlin-multiplatform")
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.serialization)
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(libs.kotlinResult)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.core)
            implementation(libs.ktor.contentNegotiations.core)
            implementation(libs.ktor.contentNegotiations.json)
            implementation(libs.koin)
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(libs.ktor.engine.cio)
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(libs.ktor.engine.js)
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(project(":data:users:network-test"))
            implementation(kotlin("test"))
            implementation(libs.kotest.assertions)
            implementation(libs.ktor.test)
        }
    }
}
