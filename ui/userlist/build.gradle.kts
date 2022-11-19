plugins {
    id("common-jetpack-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.userlist"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":data:users:core"))
    implementation(libs.jetpackCompose.material3)
    implementation(libs.jetpackCompose.navigation)
    implementation(libs.jetpackCompose.uiTooling.preview)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.coil.compose)
    implementation(libs.cokoin.viewmodel)
    debugImplementation(libs.jetpackCompose.uiTooling)

    testImplementation(project(":data:users:core-test"))
    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.coroutines.test)
}
