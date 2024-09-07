plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.features.home.presentation.implementation"
}

dependencies {
    implementation(project(":features:home:presentation:api"))
    implementation(project(":features:home:domain:api"))
    implementation(project(":templates:android"))
    implementation(project(":templates:kotlin"))
    implementation(project(":common:kotlin"))
    implementation(project(":core:api"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.viewmodel.lifecycle)
}
