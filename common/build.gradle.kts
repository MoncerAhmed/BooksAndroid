plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nl.ahmed.common"
}

dependencies {
    implementation(libs.data.room)
    ksp(libs.data.room.compiler)

    implementation(libs.networking.retrofit.gson)

    implementation(libs.androidx.core.ktx)
}
