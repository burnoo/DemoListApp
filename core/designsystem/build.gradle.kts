plugins {
    id("common-compose-library")
}

android {
    namespace = "dev.burnoo.demo.listapp.ui.theme"
}

dependencies {
    implementation(libs.material)
    implementation(libs.jetpackCompose.material3)
    implementation(libs.kamel)
}
