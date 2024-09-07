plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.common"
}

dependencies {
    implementation(libs.data.room)
    implementation(libs.networking.retrofit.gson)
    implementation(libs.androidx.core.ktx)
    
    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)
}
