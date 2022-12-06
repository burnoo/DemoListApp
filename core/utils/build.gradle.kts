plugins {
    id("common-kotlin-multiplatform")
}

kotlin.sourceSets {
    val commonTest by getting {
        dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotest.assertions)
        }
    }
}
