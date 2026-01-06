plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.performance)
    alias(libs.plugins.detekt)
}

android {
    namespace = "ru.itis.gloriaartis"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ru.itis.gloriaartis"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //modules
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.core.domain)
    implementation(projects.feature.artlist.api)
    implementation(projects.navigation.impl)
    implementation(projects.data.impl)
    implementation(projects.feature.signup.impl)
    implementation(projects.feature.login.impl)
    implementation(projects.feature.artlist.impl)
    implementation(projects.feature.artdetails.impl)
    implementation(projects.feature.profile.impl)

    //androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //compose
    implementation(libs.bundles.compose.deps)
    implementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)

    //DI
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //навигация
    implementation(libs.bundles.nav.deps)

    //firebase
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.performance)
    implementation(platform(libs.firebase.bom))

}