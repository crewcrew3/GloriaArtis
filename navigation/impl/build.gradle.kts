plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.detekt)
}

android {
    namespace = "ru.itis.gloriaartis.impl"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(projects.navigation.api)
    implementation(projects.feature.artdetails.api)
    implementation(projects.feature.artlist.api)
    implementation(projects.feature.profile.api)
    implementation(projects.feature.signup.api)
    implementation(projects.feature.login.api)

    implementation(libs.androidx.core.ktx)
    //DI
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //навигация
    implementation(libs.androidx.navigation3.runtime)
}