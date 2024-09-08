plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.features.favorites.shared"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":core:api"))
    implementation(project(":navigation"))
    implementation(project(":templates:android"))
    implementation(project(":templates:kotlin"))
    implementation(project(":common:android"))
    implementation(project(":common:kotlin"))

    implementation(project(":data:dal:api"))

    // Favorites
    implementation(project(":features:favorites:ui"))
    implementation(project(":features:favorites:presentation:api"))
    implementation(project(":features:favorites:presentation:implementation"))
    implementation(project(":features:favorites:domain:api"))
    implementation(project(":features:favorites:domain:implementation"))
    implementation(project(":features:favorites:data"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
