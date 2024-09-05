plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.feature.home"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":designsystem"))
    implementation(project(":core:api"))
    implementation(project(":navigation"))
    implementation(project(":templates:android"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
