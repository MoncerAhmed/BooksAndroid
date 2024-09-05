plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.templates.android"
}

dependencies {
    implementation(project(":navigation"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}
