plugins {
    id("common-jetpack-library")
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.jetpackCompose.material3)
    implementation(libs.jetpackCompose.navigation)
    implementation(libs.jetpackCompose.uiTooling.preview)
    debugImplementation(libs.jetpackCompose.uiTooling)
}
