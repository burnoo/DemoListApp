plugins {
    id("kotlin")
}

dependencies {
    api(libs.coroutines.core)
    api(libs.kotlinResult)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.turbine)
}
