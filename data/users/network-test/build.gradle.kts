plugins {
    id("kotlin")
}

dependencies {
    api(libs.ktor.core)
    api(libs.ktor.test)
    implementation(project(":data:users:model"))
    implementation(project(":data:users:network"))
}
