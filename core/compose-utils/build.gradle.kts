plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.core.compose.utils"
}

dependencies {
    api(project(":core:designsystem"))
    implementation(libs.kamel)
    implementation(libs.koin)
}
