import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.userlist"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:compose-utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":data:users:core"))
    implementation(libs.kotlinResult)
    @OptIn(ExperimentalComposeLibrary::class)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(libs.kamel)
    implementation(libs.cokoin.core)
    debugImplementation(compose.uiTooling)

    testImplementation(project(":data:users:core-test"))
    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.coroutines.test)

    debugImplementation(libs.jetpackCompose.uiTest.manifest)
    androidTestImplementation(project(":data:users:core-test"))
    androidTestImplementation(project(":core:compose-utils"))
    androidTestImplementation(libs.jetpackCompose.uiTest.junit)
    androidTestImplementation(libs.test.androidCore)
    androidTestImplementation(libs.kotest.assertions)
}
