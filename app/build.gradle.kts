plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias (libs.plugins.google.ksp)
    alias(libs.plugins.androidx.room)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.nishan.taskmatrix"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nishan.taskmatrix"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    room {
        // all variants will write schemas here:
        schemaDirectory("$projectDir/schemas")
        // (you can also do variant-specific:
        // schemaDirectory("debug", "$projectDir/schemas/debug")
        // )
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icon)

    // Retrofit for API calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    // Room
    implementation(libs.androidx.room.runtime)
    // annotationProcessor(libs.androidx.room.compiler) // We use ksp instead of annotationProcessor for Room compiler
    ksp(libs.androidx.room.compiler) // Use ksp for the Room compiler
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.room.testing)
    implementation(libs.kotlinx.serialization.json)
}