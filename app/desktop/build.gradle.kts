import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm { withJava() }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":core:designsystem"))
                implementation(project(":ui:userdetails"))
                implementation(project(":ui:userlist"))
                implementation(compose.desktop.currentOs)
                implementation(libs.coroutines.swing)
                implementation(libs.cokoin.core)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":core:compose-utils"))
                implementation(project(":data:users:network-test"))
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(libs.kotest.assertions)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "dev.burnoo.demo.listapp.MainKt"

        nativeDistributions {
            targetFormats(Dmg, Msi, Deb)
            packageName = "ListApp"
            packageVersion = "1.0.0"
        }
    }
}
