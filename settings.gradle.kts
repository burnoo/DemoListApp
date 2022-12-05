@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":app:android")
include(":app:desktop")

include(":core:compose-utils")
include(":core:data")
include(":core:designsystem")
include(":core:ui")
include(":core:utils")

include(":ui:userdetails")
include(":ui:userlist")

include(":data:users:core")
include(":data:users:core-test")
include(":data:users:model")
include(":data:users:network")
include(":data:users:network-test")
