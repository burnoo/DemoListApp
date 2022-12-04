import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleVersionsPlugin)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath(libs.gradle.android)
        classpath(libs.gradle.kotlin)
        classpath(libs.gradle.composeMultiplatform)
    }
}

allprojects {
    // https://discuss.kotlinlang.org/t/disabling-androidandroidtestrelease-source-set-in-gradle-kotlin-dsl-script/21448/5
    afterEvaluate {
        project.extensions.findByType<KotlinMultiplatformExtension>()?.let { kmpExtension ->
            kmpExtension.sourceSets.removeAll {
                setOf(
                    "androidAndroidTestRelease",
                    "androidTestFixtures",
                    "androidTestFixturesDebug",
                    "androidTestFixturesRelease",
                ).contains(it.name)
            }
        }
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        getStabilityLevel(candidate.version) < getStabilityLevel(currentVersion)
    }
}

fun getStabilityLevel(version: String): Int {
    val v = version.toUpperCase()
    return when {
        v.contains("ALPHA") -> 0
        v.contains("BETA") -> 1
        v.contains(Regex("RC|-M[0-9]")) -> 2
        else -> 3
    }
}
