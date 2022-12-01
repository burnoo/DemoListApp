plugins {
    id("kotlin")
}

dependencies {
    api(project(":data:users:model"))
    api(libs.kotlinResult)
    implementation(project(":data:users:network"))
    implementation(libs.coroutines.core)
    implementation(libs.koin)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.turbine)
}
