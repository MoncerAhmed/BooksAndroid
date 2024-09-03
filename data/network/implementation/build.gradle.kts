plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nl.ahmed.data.network.implementation"
}

dependencies {
    api(project(":data:network:api"))
    implementation(project(":core:api"))
    implementation(project(":common:kotlin"))

    // Retrofit
    implementation(libs.networking.retrofit)

    // Dagger
    implementation(libs.bundles.dagger)
    ksp(libs.bundles.dagger.ksp)

    implementation(libs.androidx.core.ktx)
}
