@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")

include(":core:compose-utils")
include(":core:designsystem")
include(":core:utils")

include(":ui:userdetails")
include(":ui:userlist")

include(":data:users:core")
include(":data:users:core-test")
include(":data:users:model")
include(":data:users:network")
include(":data:users:network-test")
