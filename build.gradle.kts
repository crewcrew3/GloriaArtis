// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.performance) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    detekt {
        toolVersion = "1.23.8"
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)

            xml.outputLocation.set(file("build/reports/detekt.xml"))
            html.outputLocation.set(file("build/reports/detekt.html"))
            txt.outputLocation.set(file("build/reports/detekt.txt"))
            sarif.outputLocation.set(file("build/reports/detekt.sarif"))
        }
    }
}

public fun org.gradle.api.Project.detekt(configure: org.gradle.api.Action<io.gitlab.arturbosch.detekt.extensions.DetektExtension>): kotlin.Unit { /* compiled code */ }