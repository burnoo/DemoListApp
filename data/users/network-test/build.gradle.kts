plugins {
    id("kotlin")
}

dependencies {
    api(libs.ktor.core)
    implementation(project(":data:users:network"))
    implementation(libs.ktor.test)
}
