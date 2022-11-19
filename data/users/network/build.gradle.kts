@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kotlin")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.core)
    implementation(libs.ktor.engine)
    implementation(libs.ktor.contentNegotiations.core)
    implementation(libs.ktor.contentNegotiations.json)
    implementation(libs.koin)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.ktor.test)
}
