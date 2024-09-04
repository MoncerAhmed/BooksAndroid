plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "nl.ahmed.core.implementation"
}

dependencies {
    api(project(":core:api"))
    implementation(project(":common:android"))
    implementation(project(":common:kotlin"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    // Networking
    implementation(libs.networking.retrofit.gson)
    implementation(libs.networking.okhttp3.logginginterceptor)

    // Logging
    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
