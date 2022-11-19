plugins {
    id("kotlin")
}

dependencies {
    api(project(":data:users:model"))
    implementation(project(":data:users:network"))
    implementation(libs.coroutines.core)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
}
