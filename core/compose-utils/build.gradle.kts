plugins {
    id("common-jetpack-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.core.compose.utils"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.koin)
}
