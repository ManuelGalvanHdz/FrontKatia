import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)

    // necesario para enviar objetos JSON a la API
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    androidTarget()

    jvmToolchain(17)

    sourceSets {

        val commonMain by getting {
            dependencies {

                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
                implementation(libs.compose.ui)
                implementation(libs.compose.components.resources)

                implementation(compose.materialIconsExtended)

                // Ktor Client
                implementation("io.ktor:ktor-client-core:2.3.7")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

                // JSON serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            }
        }

        val androidMain by getting {
            dependencies {

                implementation(libs.androidx.activity.compose)

                // Lifecycle
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                // Tooling
                implementation(libs.compose.uiToolingPreview)

                // Coil
                implementation("io.coil-kt:coil-compose:2.6.0")
                implementation("io.coil-kt:coil-gif:2.6.0")

                // Navigation
                implementation("androidx.navigation:navigation-compose:2.7.7")

                // Ktor engine Android
                implementation("io.ktor:ktor-client-okhttp:2.3.7")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {

    namespace = "com.ismael.kiduaventumundo.kiduaventumundo"

    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {

        applicationId = "com.ismael.kiduaventumundo.kiduaventumundo"

        minSdk = libs.versions.android.minSdk.get().toInt()

        targetSdk = libs.versions.android.targetSdk.get().toInt()

        versionCode = 1

        versionName = "1.0"
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
        }
    }

    packaging {

        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    debugImplementation(libs.compose.uiTooling)

}