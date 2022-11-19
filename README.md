# JetpackComposeTemplate
Template project for Android Jetpack Compose applications.

## Features
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Gradle Version Catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog)
- [Compose material3 UI](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary.html)
  - [Dynamic Color](https://m3.material.io/styles/color/dynamic-color/overview) support on Android 12+  
- Instrumented Tests: [Compose Testing](https://developer.android.com/jetpack/compose/testing)
- Unit Tests: [JUnit4](https://junit.org/junit4/) + [AssertJ](https://assertj.github.io/doc/)
- [gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin)
- [Renovate Bot](https://www.whitesourcesoftware.com/free-developer-tools/renovate/)

## GitHub Actions
- [Run Tests (unit + UI)](.github/workflows/tests.yml)
- [Build Debug APK](.github/workflows/build-debug-apk.yml)

## Screenshots
(Material3 default without Dynamic Color)
| Dark | Light |
|------|-------|
| <img src="https://user-images.githubusercontent.com/17478192/150538908-93227882-0970-45bb-b84c-0e6beeb8c4af.png" width="300" /> | <img src="https://user-images.githubusercontent.com/17478192/150539003-0aa26c76-ba21-4915-b222-db0452ec6814.png" width="300" /> |
