plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm { withJava() }
    js(IR) {
        browser()
    }
}
