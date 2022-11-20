plugins {
    id("kotlin")
}

dependencies {
    api(project(":data:users:model"))
    implementation(project(":data:users:core"))
    implementation(libs.koin)

    testImplementation(project(":data:users:core-test"))
    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.coroutines.test)
}
