plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "nl.ahmed.common"
}

dependencies {
    implementation(libs.data.room)
    implementation(libs.networking.retrofit.gson)
    implementation(libs.androidx.core.ktx)
}
