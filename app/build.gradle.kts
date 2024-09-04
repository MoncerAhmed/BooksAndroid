plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nl.ahmed.books"

    defaultConfig {
        applicationId = "nl.ahmed.books"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_API", "\"https://66d317bf184dce1713cf3ffd.mockapi.io/books/api/\"")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:implementation"))
    implementation(project(":data:storage:implementation"))
    implementation(project(":data:network:implementation"))
    implementation(project(":data:dal:implementation"))
    implementation(project(":common:kotlin"))

    implementation(project(":features:home"))
    implementation(project(":features:favorites"))

    // Dagger
    implementation(libs.bundles.dagger)
    ksp(libs.bundles.dagger.ksp)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
}
