plugins {
    id("common-kotlin-multiplatform")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":data:users:core"))
        }
    }
}
