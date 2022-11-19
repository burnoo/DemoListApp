plugins {
    id("kotlin")
}

dependencies {
    api(project(":data:users:model"))
    implementation(project(":data:users:network"))
    implementation(libs.coroutines.core)
    implementation(libs.koin)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
}
