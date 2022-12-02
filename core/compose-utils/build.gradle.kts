plugins {
    id("common-jetpack-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.core.compose.utils"
}

dependencies {
    api(project(":core:designsystem"))
    implementation(libs.kamel)
    implementation(libs.koin)
}
