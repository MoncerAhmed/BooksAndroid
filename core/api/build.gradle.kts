plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "nl.ahmed.core.api"
}

dependencies {
    api(libs.networking.retrofit)

    implementation(libs.inject)
    implementation(project(":common:kotlin"))
}
