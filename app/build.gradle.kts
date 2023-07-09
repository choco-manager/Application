val majorVersion = 1
val minorVersion = 0
val patchVersion = 0

plugins {
    id("com.android.application") version "8.0.2"
    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
    kotlin("android") version "1.8.20"
    kotlin("kapt") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    id("com.google.dagger.hilt.android") version "2.44"
}

@Suppress("UnstableApiUsage", "OldTargetApi")
android {
    namespace = "ru.dadyarri.choco"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.dadyarri.choco"
        minSdk = 29
        targetSdk = 33
        versionCode = majorVersion * 10000 + minorVersion * 100 + patchVersion
        versionName = "$majorVersion.$minorVersion.$patchVersion"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue(
                "string",
                "app_version",
                "${defaultConfig.versionName}${versionNameSuffix}"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue(
                "string",
                "app_version",
                "${defaultConfig.versionName}"
            )
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    kotlin {
        jvmToolchain(17)
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // AndroidX Core and Kotlin
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    // AndroidX Lifecycle and Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // DataStore
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Material Design
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended-android:1.5.0-beta03")

    // Dagger (DI)
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
}

kapt {
    correctErrorTypes = true
}