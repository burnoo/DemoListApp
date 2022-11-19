plugins {
    id("kotlin")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
}
