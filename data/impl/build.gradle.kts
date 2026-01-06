plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.detekt)
}

android {
    namespace = "ru.itis.gloriaartis.data.impl"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "DATABASE_NAME",
            "\"LOCAL_DATABASE\""
        )
        buildConfigField(
            "String",
            "METMUSEUM_BASE_URL",
            "\"https://collectionapi.metmuseum.org/public/collection/v1/\""
        )
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
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.utils)

    implementation(libs.androidx.core.ktx)
    //бд
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.ksp)
    //datasore
    implementation(libs.datastore.prefs)
    //сеть
    implementation(libs.bundles.network.deps)
    //DI
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}