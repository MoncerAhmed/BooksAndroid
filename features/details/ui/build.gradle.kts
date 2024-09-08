plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.features.details.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":designsystem:implementation"))
    implementation(project(":features:details:presentation:api"))
    implementation(project(":templates:android"))
    implementation(project(":templates:kotlin"))
    implementation(project(":common:kotlin"))
    implementation(project(":core:api"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)
}
