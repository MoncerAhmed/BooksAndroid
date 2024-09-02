plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nl.ahmed.core.implementation"
}

dependencies {
    api(project(":core:api"))

    // Dagger
    implementation(libs.bundles.dagger)
    ksp(libs.bundles.dagger.ksp)

    // Networking
    implementation(libs.networking.retrofit.gson)
    implementation(libs.networking.okhttp3.logginginterceptor)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
