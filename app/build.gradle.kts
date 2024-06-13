import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.gms.google.services)
}

android {
    namespace = "ru.dadyarri.choco"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.dadyarri.choco"
        minSdk = 29
        targetSdk = 34
        versionName = "3.7.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/ru.dadyarri.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue(
                "string",
                "app_version",
                "v${defaultConfig.versionName}"
            )
            signingConfig = signingConfigs.getByName("release")
            manifestPlaceholders["appAuthority"] =
                "${defaultConfig.applicationId}.fileprovider"
            applicationVariants.all {
                val variant = this
                variant.outputs
                    .map { it as BaseVariantOutputImpl }
                    .forEach { output ->
                        val outputFileName =
                            "${
                                rootProject.name.replace(
                                    " ",
                                    ""
                                )
                            }.apk"
                        output.outputFileName = outputFileName
                    }
            }
        }

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            manifestPlaceholders["appAuthority"] =
                "${defaultConfig.applicationId}${applicationIdSuffix}.fileprovider"
            resValue(
                "string",
                "app_version",
                "v${defaultConfig.versionName}-${defaultConfig.versionCode}"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.contentNegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.logging)
    implementation(libs.logback.classic)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging.ktx)

    runtimeOnly(libs.kotlinx.coroutines.play.services)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
